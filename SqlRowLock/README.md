#### 测试一下sql的行级锁
当connection的autoCommit=false时，sql执行更新但未commit时，其它更新该条数据的sql是等待还是顺利执行？

#### 测试结果
其它update的sql会等待，一定时间后还会超时。
select语句正常执行。

#### 结论
只要把commit设置成手动提交，sql更新语句执行之后到commit完成这段时间，会一直占有该条数据的锁。
原来，sql执行完毕之后还在占有行锁。


[blog](http://carl-zk.github.io/blog/2017/05/20/SQL%E4%B8%AD%E7%9A%84%E9%94%81/)

DBUtil
StringUtil

db.properties

抽象工厂模式

写了一个简单的测试类，没想到搞到最后连设计模式都出来了。忽然想到如果数据库从mysql切换到oracle怎么办？所以直接复习了一遍抽象工厂模式，另外考虑以后可能会遇到使用的通用工具类，所以就直接把代码重构了一下。
嗯，看起来应该很清爽，代码即注释！

jdbc的工作原理是怎样的？驱动是怎么加载的？
java.sql.Driver的加载方式有3种：
1. System.getProperty("jdbc.drivers")
2. /META-INF/services/java.sql.Driver文件里配置com.mysql.jdbc.Driver
3. Class.forname("com.mysql.jdbc.Driver")

Class.forname会执行类的初始化，即static block，static field。（不是实例化）

分析的比较好的博客
[JDBC系列 之 驱动加载原理全面解析](http://blog.csdn.net/luanlouis/article/details/29850811)

可以根据JDBC URL规则写一个自己的Driver
[Sample JDBC driver connection URL formats](https://www.ibm.com/support/knowledgecenter/SSEP7J_10.2.0/com.ibm.swg.ba.cognos.vvm_ag_guide.10.2.0.doc/c_ag_samjdcurlform.html)
[常用数据库 JDBC URL 格式](http://blog.csdn.net/ring0hx/article/details/6152528)
例如[mysql的url syntax](https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-reference-configuration-properties.html)

