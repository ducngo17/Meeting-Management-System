# Sử dụng base image OpenJDK 17
FROM eclipse-temurin:21-jdk-alpine

# Tạo thư mục làm việc
WORKDIR /app

# Thêm file jar đã build vào image
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port (không bắt buộc nhưng tốt khi dùng Swarm/K8s)
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
