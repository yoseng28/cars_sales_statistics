package mains;

import java.io.IOException;

import jobs.CarsRatioJob;
import jobs.SalesVolumeByMonthJob;

public class CarsSalesMain {

	public static void main(String[] args)
			throws IllegalArgumentException, ClassNotFoundException, IOException, InterruptedException {
		String filePath = "hdfs://192.168.184.3:8020/datasets/shanxi_cars.csv";
		String result = "hdfs://192.168.184.3:8020/results/shanxi_cars/";
		String result1 = "count";
		String result2 = "per_month_sales";
		// Configuration conf = new Configuration();
		// conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		System.setProperty("HADOOP_USER_NAME", "root");
		//CarsRatioJob.runJob(filePath, result + result1);
		SalesVolumeByMonthJob.runJob(filePath, result + result2);
	}

}
