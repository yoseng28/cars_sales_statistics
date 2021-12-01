package reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GenderAgeRangeReducer extends Reducer<Text, IntWritable, Text, Text> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values) {
			sum = sum + value.get();
		}
		context.write(key, new Text(sum + "辆"));
	}

}
