# Используем официальный образ для Kotlin
FROM openjdk:17-jdk-alpine AS build

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файлы проекта в контейнер
COPY . .

# Сборка проекта с использованием Gradle
RUN ./gradlew build

# Устанавливаем рабочую директорию для runtime-окружения
FROM openjdk:17-jdk-slim as runtime

WORKDIR /app

# Копируем скомпилированные файлы из стадии сборки
COPY --from=build /app/build/libs/*.jar /app/ApplicationKt.jar

# Определяем команду запуска
ENTRYPOINT ["java", "-jar", "/app/ApplicationKt.jar"]

# Открываем порт, который будет прослушивать приложение
EXPOSE 8080
