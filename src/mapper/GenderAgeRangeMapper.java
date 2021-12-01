package mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 统计不同年龄段的汽车销量
public class GenderAgeRangeMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.toString().equals("0")) {
			System.out.println("首行不处理！");
			return;
		}

		String[] values = value.toString().trim().split(",");
		if (values.length > 37 && values[37] != null && !values[37].trim().equals("")) {
			if (values[37].matches("^\\d*$")) {
				// 车辆上牌年份-出生年月
				int age = Integer.parseInt(values[4]) - Integer.parseInt(values[37]);
				int range_start = (age / 10 )* 10;
				int range_end = range_start + 10;
				context.write(new Text(range_start + "-" + range_end), new IntWritable(Integer.parseInt(values[11])));
			}

		}

	}
}
