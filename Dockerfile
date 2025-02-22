FROM openjdk:8-jdk-alpine

# Set working directory di dalam container
WORKDIR /app

# Copy seluruh proyek ke dalam container
COPY . .

# Beri izin eksekusi untuk mvnw
RUN chmod +x mvnw

# Build aplikasi dengan Maven tanpa menjalankan test
RUN ./mvnw clean package -DskipTests

# Copy file JAR hasil build ke lokasi yang diinginkan
COPY target/*.jar app.jar

# Tentukan port yang digunakan aplikasi
EXPOSE 9090

# Jalankan aplikasi
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
