package jobs;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import mapper.CarsRatioMapper;
import reducer.CarsRatioReducer;
import tools.HDFSTools;

public class CarsRatioJob {

	public static void runJob(String filePath, String resultPath) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance();
		job.setJarByClass(CarsRatioJob.class);
		job.setMapperClass(CarsRatioMapper.class);
		job.setReducerClass(CarsRatioReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		try {
			// 输出目录如果存在，删除
			HDFSTools.deleteFileAndDir(resultPath);
			HDFSTools.closeDFS();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileInputFormat.addInputPath(job, new Path(filePath));
		FileOutputFormat.setOutputPath(job, new Path(resultPath));

		System.out.println("MR开始运行***********************************************");
		long startTime = System.currentTimeMillis();
		boolean result = job.waitForCompletion(true);
		long endTime = System.currentTimeMillis();
		System.out.println("MR运行时间：" + (endTime - startTime) / 1000 + "秒");
		System.exit(result ? 0 : 1);
	}
}
