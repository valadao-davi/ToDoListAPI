FROM openjdk:17-jdk-slim AS build

WORKDIR /app

RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*


COPY . .

RUN mvn clean install

FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
