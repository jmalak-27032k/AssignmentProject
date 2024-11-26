Data Streaming Application 
Setup Instructions 
1. Clone the repository. 
2. Run `mvn clean install` to build the application. 
3. Run `mvn spring-boot:run` to start the application. 

Code Overview 
1.User.java: Entity representing the user data. 
2.KafkaConsumerService.java: Kafka consumer service. 
3.KafkaProducerService.java: Service for publishing messages. 
4.DataProcessorService.java: Service for processing and publishing messages. 
5.AgeCalculator.java: Utility for age calculation. 

For Testing
Run `mvn test` to execute unit tests.
