FROM openjdk:21-jdk-slim
WORKDIR /app
COPY ./target/GroceryStore.war /app/ROOT.war
EXPOSE 8081
CMD ["java", "-jar", "ROOT.war"]