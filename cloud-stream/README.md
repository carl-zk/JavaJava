# Spring Cloud Stream 
[Spring Cloud Stream Reference Guide](https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/2.1.2.RELEASE/single/spring-cloud-stream.html)

```bash
# visit localhost:15672
docker run -it --hostname rabbit-server \
 --name my-rabbit \
 -v /opt/rabbitmq/var/lib/rabbitmq:/var/lib/rabbitmq \
 -p 5672:5672 -p 15671:15671 -p 15672:15672 \
 rabbitmq:3-management
```



