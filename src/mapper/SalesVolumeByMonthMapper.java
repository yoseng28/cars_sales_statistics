package mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//统计每个月汽车销量
public class SalesVolumeByMonthMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.toString().equals("0")) {
			System.out.println("首行不处理！");
			return;
		}
		String[] values = value.toString().trim().split(",");
		if (values.length > 11 && values[11] != null && !values[11].trim().equals("") && values[1] != null
				&& !values[1].trim().equals("")) {
			context.write(new IntWritable(Integer.parseInt(values[1])), new IntWritable(Integer.parseInt(values[11])));
		}
	}
}
