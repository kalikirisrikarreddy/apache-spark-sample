package amazon.s3;

import java.io.File;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3ObjectsManager {

	public static void main(String[] args) {
		S3Client s3Client = S3Client.builder().region(Region.US_EAST_1)
				.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(null, null))).build();
		s3Client.putObject(
				PutObjectRequest.builder().bucket("apache-spark-sample-code-artifacts-bucket-1")
						.key("apache-spark-sample.jar").build(),
				RequestBody.fromFile(new File("target/apache-spark-sample-1.0.0-jar-with-dependencies.jar")));
		s3Client.putObject(
				PutObjectRequest.builder().bucket("emr-with-apache-spark-bucket-1").key("input/sample.txt").build(),
				RequestBody.fromFile(new File("sample.txt")));
	}

}
