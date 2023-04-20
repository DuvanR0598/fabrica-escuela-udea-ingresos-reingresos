FROM gradle:7.6.0-jdk11-alpine AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./

COPY src/ ./src/

RUN gradle build --no-daemon -x test

FROM amazoncorretto:11-alpine3.13-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/ingresos-reingresos-backend-1.0.0.jar ./ingresos-reingresos-backend-1.0.0.jar

EXPOSE 8080

CMD ["java", "-jar", "ingresos-reingresos-backend-1.0.8.jar"]