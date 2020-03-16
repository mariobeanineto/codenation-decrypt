FROM openjdk:11.0.4-jdk
VOLUME /tmp
COPY /target/decrypt*.jar decrypt.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/decrypt.jar"]
