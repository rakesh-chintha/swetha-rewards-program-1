# Reward Points Application

## Running the Application

1. Clone the repository: `git clone https://github.com/your-username/reward-points.git`
2. Build the application: `mvn clean package`
3. Run the application: `java -jar target/reward-points-0.0.1-SNAPSHOT.jar`
4. Access the REST API: `http://localhost:8080/api/reward-points`

## API Documentation

### GET /{userId}/total

Returns the total reward points for a user.

### POST /transactions

Creates a new transaction.

## Testing

Run `mvn test` to execute unit tests and integration tests.

## Logging

The application uses Logback for logging. Log levels can be adjusted in the `application.properties` file.