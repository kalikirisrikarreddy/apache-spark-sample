package apache.spark;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class SparkSample {

	public static void main(String[] args) {

		String appName = "SparkSample";

		SparkSession sparkSession = SparkSession.builder().appName(appName).getOrCreate();

		JavaSparkContext sparkContext = new JavaSparkContext(sparkSession.sparkContext());

		JavaRDD<String> textFile = sparkContext.textFile("s3://emr-with-apache-spark-bucket-1/input/*");
		JavaPairRDD<String, Integer> counts = textFile.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((a, b) -> a + b);
		counts.saveAsTextFile("s3://emr-with-apache-spark-bucket-1/output-" + System.currentTimeMillis());

		sparkContext.close();

		sparkContext.stop();
	}

}