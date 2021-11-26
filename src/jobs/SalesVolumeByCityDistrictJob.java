package jobs;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import mapper.SalesVolumeByCityDistrictMapper.CityDistrictMapper;
import mapper.SalesVolumeByCityDistrictMapper.CityMapper;
import reducer.SalesVolumeByCityDistrictReducer.CityDistrictReducer;
import reducer.SalesVolumeByCityDistrictReducer.CityReducer;
import tools.HDFSTools;

public class SalesVolumeByCityDistrictJob {

	public static void runJob(Configuration conf, String filePath, String cityDistrictPath,String cityPath)
			throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		try {
			// 输出目录如果存在，删除
			HDFSTools.deleteFileAndDir(cityDistrictPath);
			HDFSTools.deleteFileAndDir(cityPath);
			HDFSTools.closeDFS();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// 此job虽然未调用参数（月份），但需将conf传递
		Job job = Job.getInstance(conf);
		job.setJarByClass(SalesVolumeByCityDistrictJob.class);
		job.setMapperClass(CityDistrictMapper.class);
		job.setReducerClass(CityDistrictReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(filePath));
		FileOutputFormat.setOutputPath(job, new Path(cityDistrictPath));
		System.out.println("MR开始运行***********************************************");
		long startTime = System.currentTimeMillis();
		boolean result = job.waitForCompletion(true);
		long endTime = System.currentTimeMillis();
		System.out.println("MR运行时间：" + (endTime - startTime) / 1000 + "秒");
		if (result) {
			Job job_ = Job.getInstance(conf);
			job_.setJarByClass(SalesVolumeByCityDistrictJob.class);
			job_.setMapperClass(CityMapper.class);
			job_.setReducerClass(CityReducer.class);
			job_.setMapOutputKeyClass(Text.class);
			job_.setMapOutputValueClass(IntWritable.class);
			job_.setOutputKeyClass(Text.class);
			job_.setOutputValueClass(IntWritable.class);
			FileInputFormat.addInputPath(job_, new Path(cityDistrictPath));
			FileOutputFormat.setOutputPath(job_, new Path(cityPath));
			System.out.println("MR开始运行***********************************************");
			long startTime2 = System.currentTimeMillis();
			boolean result2 = job_.waitForCompletion(true);
			long endTime2 = System.currentTimeMillis();
			System.out.println("MR运行时间：" + (endTime2 - startTime2) / 1000 + "秒");
			System.exit(result2 ? 0 : 1);
		}
	}

}
