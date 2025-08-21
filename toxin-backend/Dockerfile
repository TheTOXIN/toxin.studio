# Stage 1: Сборка jar с помощью Maven
FROM maven:3.9.9-eclipse-temurin-21-alpine AS maven-builder
WORKDIR /build
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Извлечение слоёв из JAR
FROM eclipse-temurin:21-alpine AS builder
VOLUME /tmp
WORKDIR /build
COPY --from=maven-builder /build/target/*.jar application.jar
#ARG JAR_FILE=/build/target/*.jar
#COPY --from=maven-builder ${JAR_FILE} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

# Stage 3: Финальный минимальный образ
FROM eclipse-temurin:21-alpine
WORKDIR /build
COPY --from=builder /build/extracted/dependencies/ ./
COPY --from=builder /build/extracted/spring-boot-loader/ ./
COPY --from=builder /build/extracted/snapshot-dependencies/ ./
COPY --from=builder /build/extracted/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
