FROM amazoncorretto:21-alpine3.18-full
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} untitled.jar
ENTRYPOINT ["java", "${JAVA_OPTS}","-jar","/untitled.jar"]
