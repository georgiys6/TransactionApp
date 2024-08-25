## Use the official Micronaut Docker image
#FROM ghcr.io/micronaut-projects/micronaut-gradle:latest as build
#
#WORKDIR /home/app
#
#COPY . .
#
#RUN ./gradlew build
#
#FROM openjdk:21-jdk-alpine
#
#WORKDIR /app
#
#COPY --from=build /home/app/build/libs/dev.georgiys-*.jar /app/micronautapp.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "/app/micronautapp.jar"]


# Используем официальный образ для Kotlin
FROM openjdk:17-jdk-slim as build

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файлы проекта в контейнер
COPY . .

# Сборка проекта с использованием Gradle
RUN ./gradlew build

# Устанавливаем рабочую директорию для runtime-окружения
FROM openjdk:24-jdk-slim as runtime

WORKDIR /app

# Копируем скомпилированные файлы из стадии сборки
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Определяем команду запуска
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Открываем порт, который будет прослушивать приложение
EXPOSE 8080
