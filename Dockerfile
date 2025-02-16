FROM openjdk:8-jdk-alpine

# Set working directory di dalam container
WORKDIR /app

# Copy file JAR aplikasi ke dalam container
COPY target/*.jar app.jar

# Tentukan port yang digunakan aplikasi
EXPOSE 9090

# Jalankan aplikasi
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
