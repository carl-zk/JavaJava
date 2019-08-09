FROM openjdk:8-jre-alpine
# add bash to image, so you can 'docker exec -it xxx bash'
# RUN apk add --no-cache bash

ENV TZ=Asia/Shanghai
RUN echo $TZ /etc/localtime && echo $TZ > /etc/timezone

ENV JAVA_OPTS="-Dspring.profiles.active=ut"

COPY app.jar /app.jar
COPY run.sh /run.sh

WORKDIR /

EXPOSE 8080

# run application with this command line
CMD ["/bin/sh", "/run.sh"]