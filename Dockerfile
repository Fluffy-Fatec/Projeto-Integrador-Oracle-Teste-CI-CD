FROM maven:3.8.3-openjdk-17

ENV PROJECT_HOME /usr/src/fluffyapp
ENV JAR_NAME fluffyapp.jar

# Create destination directory
RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

# Bundle app source
COPY . .

# Package the application as a JAR file
RUN mvn clean package -DskipTests

# Move file
RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/
RUN mv $PROJECT_HOME/src/main/resources/application.properties $PROJECT_HOME/

# Set the working directory
WORKDIR $PROJECT_HOME

ENTRYPOINT ["java", "-jar", "-Dspring.config.location=application.properties", "fluffyapp.jar"]