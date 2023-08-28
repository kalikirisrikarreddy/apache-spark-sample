package apache.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkSample {

	public static void main(String[] args) {
		// Set your Spark application name
		String appName = "SparkSample";

		// Initialize Spark configuration
		SparkConf sparkConf = new SparkConf().setAppName(appName);

		// Create a Spark context
		JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

		// Your Spark processing logic goes here
		JavaRDD<String> textFile = sparkContext.textFile("s3://emr-with-apache-spark-bucket-1/input/*");
		JavaPairRDD<String, Integer> counts = textFile.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((a, b) -> a + b);
		counts.saveAsTextFile("s3://emr-with-apache-spark-bucket-1/output-" + System.currentTimeMillis() + "/*");

		// Close the Spark context when done
		sparkContext.close();
	}

}