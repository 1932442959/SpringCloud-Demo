FROM java:8
EXPOSE 10010
VOLUME /tmp
ADD gateway.jar gateway-docker.jar
RUN sh -c 'touch /gateway-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway-docker.jar" ]