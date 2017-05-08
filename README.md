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

* GET /feedback -> all feedbacks
* GET /feedback?user=UserName -> all feedback for user with name "UserName"

* POST /feedback -> add new feedback
 
  * request JSON body 
  ```json
  { 
    "user" : "testUser", 
    "message" : "New Message" 
  }
  ```

* Response example

```json
{ 
  "id" : 1,
  "user" : "testUser", 
  "message" : "New Message",
  "createDate": "2017-05-08T11:41:20.502+02:00[Europe/Bratislava]"
}
```
