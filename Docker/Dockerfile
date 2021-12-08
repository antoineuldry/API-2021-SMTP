FROM openjdk:11

EXPOSE 8080
EXPOSE 2021

COPY MockMock/MockMock.jar /usr/src/MockMock/MockMock.jar
WORKDIR /usr/src/MockMock

CMD ["java", "-jar", "MockMock.jar", "-p", "2021", "-h", "8080"]