FROM maven:3.8.3-openjdk-17

ENV PROJECT_HOME /usr/src/fluffyapp
ENV JAR_NAME fluffyapp.jar

# Create destination directory
RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

# Copy the contents of the 'backend' directory into the container
COPY ./backend/ .

# Package the application as a JAR file
RUN mvn -f backend/pom.xml clean package -DskipTests

# Move the JAR file
RUN mv $PROJECT_HOME/backend/target/$JAR_NAME $PROJECT_HOME/

# Set the working directory
WORKDIR $PROJECT_HOME

ENTRYPOINT ["java", "-jar", "-Dspring.config.location=application.properties", "fluffyapp.jar"]
