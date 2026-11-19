FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml ./
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-jammy AS runtime
RUN apt-get update && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd --system taskforge && useradd --system --gid taskforge --home-dir /app taskforge
WORKDIR /app
COPY --from=build --chown=taskforge:taskforge /build/target/taskforge-1.0-SNAPSHOT.jar app.jar
USER taskforge
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
