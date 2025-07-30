FROM eclipse-temurin:17-jdk-alpine
# Define el directorio de trabajo
WORKDIR /app
# Copia el JAR generado
COPY target/*.jar app.jar
# Expone el puerto de la aplicación
EXPOSE 8080
# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
