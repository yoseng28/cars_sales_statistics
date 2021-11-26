package mains;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import jobs.CarsRatioJob;
import jobs.SalesVolumeByCityDistrictJob;
import jobs.SalesVolumeByMonthJob;

public class CarsSalesMain {

	public static void main(String[] args)
			throws IllegalArgumentException, ClassNotFoundException, IOException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "root");
		String filePath = "hdfs://192.168.184.3:8020/datasets/shanxi_cars.csv";
		String result = "hdfs://192.168.184.3:8020/results/shanxi_cars/";
		String result1 = "count";
		String result2 = "per_month_sales";
		String result3 = "cityDistrict";
		String result4 = "city";
		// conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		//CarsRatioJob.runJob(filePath, result + result1);
		//SalesVolumeByMonthJob.runJob(filePath, result + result2);
		// 设置月份，通过conf传递参数至mapper
		Configuration conf = new Configuration();
		conf.set("month", "3");
		SalesVolumeByCityDistrictJob.runJob(conf, filePath, result + result3, result + result4);
	}

}
