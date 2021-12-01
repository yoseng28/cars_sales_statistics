package mains;

import java.io.IOException;

import jobs.GenderAgeRangeJob;

public class CarsSalesMain {

	public static void main(String[] args)
			throws IllegalArgumentException, ClassNotFoundException, IOException, InterruptedException {
		// conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		System.setProperty("HADOOP_USER_NAME", "root");
		String filePath = "hdfs://192.168.184.3:8020/datasets/shanxi_cars.csv";
		String result = "hdfs://192.168.184.3:8020/results/shanxi_cars/";
		String result2 = "gender&age";
		
		// 设置月份，通过conf传递参数至mapper
		//Configuration conf = new Configuration();
		//conf.set("month", "3");
		//SalesVolumeByCityDistrictJob.runJob(conf, filePath, result + result3, result + result4);
		
		GenderAgeRangeJob.runJob(filePath, result + result2);
	}

}
