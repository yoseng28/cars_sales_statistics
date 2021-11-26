package reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SalesVolumeByCityDistrictReducer {

	public static class CityDistrictReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable v : values) {
				sum += v.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}
	
	public static class CityReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable v : values) {
				sum += v.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}
	
}
