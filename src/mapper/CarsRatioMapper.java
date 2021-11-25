package mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 统计‘乘用车’和‘商用车’各自数量占比
public class CarsRatioMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.toString().equals("0")) {
			System.out.println("首行不处理！");
			return;
		}
		String[] values = value.toString().trim().split(",");
		if (values != null && values.length > 10 && values[10] != null) {
			if (values[10].equals("非营运")) {
				context.write(new Text("乘用车"), new IntWritable(1));
			} else {
				context.write(new Text("商用车"), new IntWritable(1));
			}
		}
	}

}
