FROM java:8
EXPOSE 8082
VOLUME /tmp
ADD user-service.jar user-service-docker.jar
RUN sh -c 'touch /user-service-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user-service-docker.jar" ]