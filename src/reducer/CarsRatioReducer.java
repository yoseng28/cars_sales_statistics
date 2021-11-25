package reducer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//统计‘乘用车’和‘商用车’各自数量占比
public class CarsRatioReducer extends Reducer<Text, IntWritable, Text, Text> {
	
	int allCars = 0;
	Map<String, Integer> maps = new HashMap<>();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values) {
			sum = sum + value.get();
		}
		// 所有‘乘用车’和‘商用车’数量
		allCars = allCars + sum;
		maps.put(key.toString(), sum);
	}

	public void cleanup(Context context) throws IOException, InterruptedException {
		// ‘乘用车’和‘商用车’集合
		Set<String> keys = maps.keySet();
		for (String k : keys) {
			int count = maps.get(k);
			// 格式化小数
			DecimalFormat df = new DecimalFormat("0.00");
			String percent = df.format((double) count / allCars);
			context.write(new Text(k), new Text(percent));
		}
	}

}
