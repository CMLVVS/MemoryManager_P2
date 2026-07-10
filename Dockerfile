# Usar imagen con Maven y Java 17 preinstalados
FROM maven:3.9.4-eclipse-temurin-17-alpine

# Crear directorio de trabajo
WORKDIR /app

# Copiar código fuente
COPY src /app/src
COPY pom.xml /app/

# Compilar el proyecto
RUN mvn clean compile

# Comando para ejecutar el simulador
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.cvc.memory.simulator.MainSimulator"]