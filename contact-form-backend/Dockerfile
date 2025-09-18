# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Bağımlılıkları önceden çek (cache verimli olsun)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Kaynaklar
COPY src ./src

# Jar üret
RUN mvn -q -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Oluşan jar'ı al (ad değişken olabilir diye wildcard)
COPY --from=build /app/target/*-SNAPSHOT.jar /app/app.jar
# Eğer snapshot değilse: COPY --from=build /app/target/*.jar /app/app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport"
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
