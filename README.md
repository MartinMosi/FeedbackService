# Feedback Service

Simple Spring boot "microservice" application.

## Build Application 
mvn clean install

## Run in Development mode
java -jar FeedbackService-0.0.1.jar -Dspring.profiles.active=dev

## Run in Production mode 
java -jar FeedbackService-0.0.1.jar -Dspring.profiles.active=prod

## Available REST API

http://localhost:8080/feedback

## REST API Documentation

http://localhost:8080/docs/index.html
