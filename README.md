# Execution steps

1. Clone this project onto your local machine.
2. Import the project into IDE(Eclipse, IntelliJ etc.) of your choice as a maven project.
3. Run `mvn install` on the project.
4. Update the credentials at line number 16 of amazon.s3.S3ObjectsManager.java to credentials of a user in your AWS account.
5. Edit the line numbers 18 and 22, to bucket names of your choice. Just make sure to create the buckets beforehand.
6. Run amazon.s3.S3ObjectsManager.java to upload the build artifact and input text file to S3.
