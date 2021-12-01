package reducer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GenderRatioReducer extends Reducer<Text, IntWritable, Text, Text> {

	int allGenderCount = 0;
	Map<String, Integer> maps = new HashMap<>();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int count = 0;
		for (IntWritable v : values) {
			count = count + v.get();
		}
		allGenderCount = allGenderCount + count;
		maps.put(key.toString(), count);
	}

	public void cleanup(Context context) throws IOException, InterruptedException {
		Set<String> keys = maps.keySet();
		for (String k : keys) {
			int count = maps.get(k);
			// 格式化小数
			DecimalFormat df = new DecimalFormat("0.00");
			String percent = df.format((double) count / allGenderCount);
			context.write(new Text(k), new Text(percent));
		}
	}

}
