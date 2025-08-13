# -------- Stage 1: Build with Gradle (no host tools required)
FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace

# Copy Gradle wrapper + build files first (better caching)
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Download deps
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies > /dev/null 2>&1 || true

# Copy source last
COPY src ./src

# Build the fat jar
RUN ./gradlew --no-daemon clean bootJar -x test

# -------- Stage 2: Runtime (small JRE image)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar from build stage
COPY --from=build /workspace/build/libs/*.jar app.jar

# Cloud Run expects the app to listen on 8080
ENV PORT=8080
EXPOSE 8080

# Start
ENTRYPOINT ["java","-jar","/app/app.jar"]