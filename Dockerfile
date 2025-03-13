# Etapa 1: Construcción con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar los archivos del proyecto y compilar
COPY pom.xml .
COPY src ./src
RUN mvn clean package -U -DskipTests

# Etapa 2: Imagen más ligera con Java 21 para ejecutar la aplicación
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar el JAR generado desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]