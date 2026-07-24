FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/misbahul-platform-1.0.0-SNAPSHOT.jar app.jar

# Convert postgresql:// to jdbc:postgresql:// if needed
ENTRYPOINT ["sh", "-c", "\
  if [ -n \"$DATABASE_URL\" ]; then \
    JDBC_URL=$(echo $DATABASE_URL | sed 's|^postgresql://|jdbc:postgresql://|'); \
    export DATABASE_URL=$JDBC_URL; \
  fi; \
  java -jar app.jar"]
