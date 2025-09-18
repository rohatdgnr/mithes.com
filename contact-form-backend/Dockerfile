# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Sadece pom.xml'i kopyala ve bağımlılıkları indir (cache optimize)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Kaynak kodları kopyala ve jar üret
COPY src ./src
RUN mvn -q -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Build aşamasında üretilen JAR'ı al
COPY --from=build /app/target/*.jar /app/app.jar

# Ortam değişkenleri
ENV JAVA_OPTS="-XX:+UseContainerSupport"
EXPOSE 8080

# Uygulamayı başlat
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
