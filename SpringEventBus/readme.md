> 这个代码是学习spring-publish/subscribe的demo

## spring-framework介绍
[http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
spring-framework还用介绍?come on!

## Publish/Subscribe
spring-framework本身提供一套事件的发布/订阅机制,官方文档中的介绍很easy,通过一个小demo就可以让我明白如何使用.
但是,spring-framework框架真是太大了,单就这个Publish/Subscribe就涉及太多接口太多类,接口实现关系和类的继承关系让
我有些难以消化.就是因为它太大而全,所以不太适合初级学习,我找到一个android版的EventBus框架,文档介绍和代码比较容易理
解,可以从这里先阅读理解一下.[EventBus源码解析](http://a.codekk.com/detail/Android/Trinea/EventBus%20%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)

Publish/Subscribe支持同步,异步,事务,优先级,返回异步结果

## 目标
写一个简单实用EventBus框架.

--------------------------------------------------------------------------------------

1.
按spring文档中的示例写出的事件发布/订阅是同步的.所谓同步,就是发布者和接收者在同一个线程.
点击'http://localhost:8888/eventbus/controller/send'之后浏览器一直处于等待状态.

2.
基本上,项目中异步的情景更多,那如何设置成异步的呢?通过查看源代码可以发现AbstractApplicationContext这个类
是所有ApplicationContext类的父类,它实现了事件的publishEvent方法,并且通过initApplicationEventMulticaster
方法来指定Listeners的处理方式.
如果在xml中配置类名为`applicationEventMulticaster`的bean,则context就用这个,否则就new一个SimpleApplicationEventMulticaster
设置给context.new出来的这个taskExecutor是null,所以
```java
public void multicastEvent(final ApplicationEvent event, ResolvableType eventType) {
	ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
	for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
		Executor executor = getTaskExecutor();
		if (executor != null) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					invokeListener(listener, event);
				}
			});
		}
		else {
			invokeListener(listener, event);
		}
	}
}
```
最终SimpleApplicationEventMulticaster发布一个event时,直接调用invokeListener(listener, event)方法,listener
就会被叫来处理这个event,此时接收者和发布者在同一个线程中,所以默认的是同步处理.
```java
package org.springframework.context.support;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext, DisposableBean {

	/**
	 * Name of the ApplicationEventMulticaster bean in the factory.
	 * If none is supplied, a default SimpleApplicationEventMulticaster is used.
	 * @see org.springframework.context.event.ApplicationEventMulticaster
	 * @see org.springframework.context.event.SimpleApplicationEventMulticaster
	 */
	public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";


	/** Helper class used in event publishing */
	private ApplicationEventMulticaster applicationEventMulticaster;

	/** Statically specified listeners */
	private final Set<ApplicationListener<?>> applicationListeners = new LinkedHashSet<ApplicationListener<?>>();

	/** ApplicationEvents published early */
	private Set<ApplicationEvent> earlyApplicationEvents;


	public void publishEvent(ApplicationEvent event) {
		publishEvent(event, null);
	}

	public void publishEvent(Object event) {
		publishEvent(event, null);
	}

	protected void publishEvent(Object event, ResolvableType eventType) {
		Assert.notNull(event, "Event must not be null");
		if (logger.isTraceEnabled()) {
			logger.trace("Publishing event in " + getDisplayName() + ": " + event);
		}

		// Decorate event as an ApplicationEvent if necessary
		ApplicationEvent applicationEvent;
		if (event instanceof ApplicationEvent) {
			applicationEvent = (ApplicationEvent) event;
		}
		else {
			applicationEvent = new PayloadApplicationEvent<Object>(this, event);
			if (eventType == null) {
				eventType = ((PayloadApplicationEvent)applicationEvent).getResolvableType();
			}
		}

		// Multicast right now if possible - or lazily once the multicaster is initialized
		if (this.earlyApplicationEvents != null) {
			this.earlyApplicationEvents.add(applicationEvent);
		}
		else {
			getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
		}

		// Publish event via parent context as well...
		if (this.parent != null) {
			if (this.parent instanceof AbstractApplicationContext) {
				((AbstractApplicationContext) this.parent).publishEvent(event, eventType);
			}
			else {
				this.parent.publishEvent(event);
			}
		}
	}



	/**
	 * Initialize the ApplicationEventMulticaster.
	 * Uses SimpleApplicationEventMulticaster if none defined in the context.
	 * @see org.springframework.context.event.SimpleApplicationEventMulticaster
	 */
	protected void initApplicationEventMulticaster() {
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		if (beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)) {
			this.applicationEventMulticaster =
					beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
			if (logger.isDebugEnabled()) {
				logger.debug("Using ApplicationEventMulticaster [" + this.applicationEventMulticaster + "]");
			}
		}
		else {
			this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
			beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
			if (logger.isDebugEnabled()) {
				logger.debug("Unable to locate ApplicationEventMulticaster with name '" +
						APPLICATION_EVENT_MULTICASTER_BEAN_NAME +
						"': using default [" + this.applicationEventMulticaster + "]");
			}
		}
	}
}
```
发布者只管发布,接听者只管接听,ApplicationEventMulticaster类起到辅助作用,让它来管理listeners.

那么,很简单了,只需要在xml中配置这个bean就OK了
```xml
<bean id="applicationEventMulticaster" class="org.springframework.context.event.SimpleApplicationEventMulticaster">
    <property name="taskExecutor">
        <bean class="org.springframework.core.task.SimpleAsyncTaskExecutor"/>
    </property>
</bean>
```
