# Usar imagen base con Java 17
FROM openjdk:17-jdk-slim

# Crear directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el código fuente
COPY src /app/src
COPY pom.xml /app/

# Instalar Maven para compilar
RUN apt-get update && apt-get install -y maven

# Compilar el proyecto
RUN mvn clean compile

# Comando para ejecutar el simulador
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.cvc.memory.simulator.MainSimulator"]
