# Stage 1: Build
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /usr/src/fluffyapp
COPY . .
RUN mvn -f backend/pom.xml clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-alpine
WORKDIR /usr/src/fluffyapp
COPY --from=build /usr/src/fluffyapp/backend/target/*.jar fluffyapp.jar
COPY --from=build /usr/src/fluffyapp/wallet /usr/src/fluffyapp/wallet
ENTRYPOINT ["java", "-jar", "-Doracle.net.tns_admin=/usr/src/fluffyapp/wallet", "fluffyapp.jar"]