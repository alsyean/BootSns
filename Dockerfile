FROM java:8
VOLUME /tmp
ARG JAR_FILE=./target/BootSns-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} MyDiary.jar 
ENTRYPOINT ["java","-jar","/MyDiary.jar"]
