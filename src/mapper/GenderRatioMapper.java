package mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 统计男女买车比例
public class GenderRatioMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.toString().equals("0")) {
			System.out.println("首行不处理！");
			return;
		}
		String[] values = value.toString().trim().split(",");
		if (values.length > 38 && values[38] != null && !values[38].trim().equals("")) {
			if (values[38].equals("男性") | values[38].equals("女性")) {
				context.write(new Text(values[38]), new IntWritable(1));
			}
		}

	}

}
