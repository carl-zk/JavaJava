> 有自己的经验，也有从老师傅那里偷来的。
> 编程就跟练武一样，只要练好内功，框架再怎么更新都只是花架子而已。N年前的规范拿到现在依然是最棒的，不得不感叹前辈的高明。

# MY BEST PRACTISE

## 代码模板生成器
[https://www.jhipster.tech/](https://www.jhipster.tech/)

## API 文档
[https://docsify.js.org/#/](https://docsify.js.org/#/)

## IDEA Plugins
[alibaba-p3c](https://github.com/alibaba/p3c/tree/master/idea-plugin)
[sonarlint](https://plugins.jetbrains.com/plugin/7973-sonarlint)
代码风格：google-code-style.xml

## IDEA + JRebel
[JRebel](blank)支持热部署，使用方法：

[https://zeroturnaround.com/software/jrebel/quickstart/intellij/?run=ide#!/reload](https://zeroturnaround.com/software/jrebel/quickstart/intellij/?run=ide#!/reload)
<pre>
1. [Activation](https://my.jrebel.com/account/how-to-activate)
2. Press the jrebel-ide-icon Run with JRebel 
3. 
  a. Open View > Tool Windows > JRebel. The JRebel Panel window opens.
  b. Enable JRebel for the desired modules by selecting the  column checkboxes.
4.
  a. Restart your Java application using jrebel-ide-icon Run with JRebel (or from the command line).
  b. Change something in the application code.
  c. Use Build > Build Project command to compile classes and update your application.
</pre>
激活要先完成第一步的流程。
因为是第一次用JRebel，所以先JRebel启动，然后第3步完成勾选，再重新启动JRebel。第二次就直接使用JRebel启动项目，无须勾选。

## [GO]()
### 使用内置Jetty容器启动web项目。
pom.xml
```xml
<packaging>war</packaging>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>

<plugin>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-maven-plugin</artifactId>
    <version>9.4.9.v20180320</version>
</plugin>
```
add configuration > maven 
在command line 里：`clean:clean jetty:run`

### map2json
pom.xml
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.5</version>
</dependency>
```
spring-mvc.xml
```xml
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
    <property name="messageConverters">
        <list>
            <ref bean="mappingJacksonHttpMessageConverter"/>
        </list>
    </property>
</bean>

<bean id="mappingJacksonHttpMessageConverter"
      class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
    <property name="supportedMediaTypes">
        <list>
            <bean class="org.springframework.http.MediaType">
                <constructor-arg index="0" value="text"/>
                <constructor-arg index="1" value="plain"/>
                <constructor-arg index="2" value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.MediaType">
                <constructor-arg index="0" value="*"/>
                <constructor-arg index="1" value="*"/>
                <constructor-arg index="2" value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.MediaType">
                <constructor-arg index="0" value="text"/>
                <constructor-arg index="1" value="*"/>
                <constructor-arg index="2" value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.MediaType">
                <constructor-arg index="0" value="application"/>
                <constructor-arg index="1" value="json"/>
                <constructor-arg index="2" value="UTF-8"/>
            </bean>
        </list>
    </property>
</bean>
```    
这里仅支持UTF-8编码，即请求头Accept的编码，如：`Accept:application/json;charset=utf-8`，其它的可能会乱码或没有对应的converter。

## [bean-based-demo](https://github.com/carl-zk/JavaJava/tree/master/bean-based-demo)
bean-based configuration 
Spring Framwork + Hibernate

## [spring-cloud](https://github.com/carl-zk/JavaJava/tree/master/core)



