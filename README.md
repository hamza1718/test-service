# test-service
Sample code for playground and evaluation purposes

### What this repo contains
- The code in this repo is a small subset of the **actual application**.
- It has a couple basic API endpoints along with the entities and their data access interfaces.
- It spins up an in memory H2 database, which is destroyed as soon as the app is shutdown.

### Running the application
- This is a SpringBoot application.
- Clone the repo, and run the main class. No additional config is required.

### What you need to do
1. Please add unit tests for **PasswordResetService.java**
2. Please add an integration test that tests the creation of a User and verifies if the user is successfully persisted in the DB.
