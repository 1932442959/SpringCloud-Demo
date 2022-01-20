FROM java:8
EXPOSE 8081
VOLUME /tmp
ADD order-service.jar order-service-docker.jar
RUN sh -c 'touch /order-service-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /order-service-docker.jar" ]