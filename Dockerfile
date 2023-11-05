FROM maven:3.8.3-openjdk-17

ENV PROJECT_HOME /usr/src/fluffyapp

# Define o diretório de trabalho no contêiner
WORKDIR $PROJECT_HOME

# Copia o conteúdo do projeto para o contêiner
COPY . .

# Executa o Maven para construir o projeto
RUN mvn -f backend/pom.xml clean package -DskipTests

# Move o arquivo JAR para a raiz do projeto no contêiner
RUN mv backend/target/*.jar $PROJECT_HOME/fluffyapp.jar

# Define a entrada do contêiner
ENTRYPOINT ["java", "-jar", "-Dspring.config.location=application.properties", "fluffyapp.jar"]
