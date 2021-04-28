FROM openjdk:15
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} untitled.jar
ENTRYPOINT ["java", "-XX:HeapDumpPath=/mnt/heapdumps/untitled/$MY_POD_NAME", "-XX:+HeapDumpOnOutOfMemoryError","-jar","/untitled.jar"]
