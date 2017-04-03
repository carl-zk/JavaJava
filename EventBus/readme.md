> 这个是我自己写的EventBus, Spring自带的Publish/Subscribe太复杂,索性自己写个简单的,然后慢慢添功能.
这样也便于理解实现机制.

## EventBus tutorial
 目前只有3个类:EventBus, EventHandler, Subscribe.
 不支持事务,同步,优先级,订阅者撤销.

1.
在spring项目pom.xml中引入
```xml
<dependency>
    <groupId>moc.oreh</groupId>
    <artifactId>event-bus</artifactId>
    <version>1.0</version>
</dependency>
```
在applicationContext.xml文件中加入EventBus bean
```xml
<bean class="moc.oreh.eventbus.EventBus" destroy-method="destroy">
    <constructor-arg name="corePoolSize" value="8"/>
    <constructor-arg name="maximumPoolSize" value="32"/>
    <constructor-arg name="keepAliveTime" value="300"/>
</bean>
```

2.
新建xxxEvent和xxxEventHandler
```java
public class OnlineEvent {
    private User user;

    public OnlineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

@Component
public class OnlineEventHandler {
    @Subscribe
    public void handle(OnlineEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.print("handle event: " + event.getClass().getName() + " ---> ");
        System.out.println(event.getUser());
    }
}
```

3.
通过EventBus发布事件,带有Subscribe标签的EventHandler会异步处理这个事件.
```java
@Controller
public class MyController {
    @Autowired
    private EventBus eventBus;

    @RequestMapping("/online")
    public User online() {
        OnlineEvent onlineEvent = new OnlineEvent(new User("小红", 17));
        eventBus.publish(onlineEvent, this);
        return new User("xfdosa", 3);
/*        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    OnlineEvent onlineEvent = new OnlineEvent(new User("小红", 17));
                    eventBus.publish(onlineEvent, this);
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }*/
    }
}
```


[示例代码](https://github.com/carl-zk/JavaJava/tree/master/SpringEventBus)


和其它publish/subscribe框架相比,我这个应该是最容易理解的.如果需要事务,优先级等功能,可以参考spring源码进行修改.
我会一直丰富它的功能,能把它用到以后的工作中是最好的.

