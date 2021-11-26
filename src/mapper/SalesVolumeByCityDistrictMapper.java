package mapper;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SalesVolumeByCityDistrictMapper {

	//统计某个月市区县汽车销量
	public static class CityDistrictMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			if (key.toString().equals("0")) {
				System.out.println("首行不处理！");
				return;
			}
			String[] values = value.toString().trim().split(",");
			if (values.length > 11 && values[11] != null && !values[11].trim().equals("") && values[2] != null
					&& !values[2].trim().equals("")) {
				// 从外部传递参数：月份month
				Configuration conf = context.getConfiguration();
				String month = conf.get("month");
				if (values[1].equals(month)) {
					context.write(new Text(month + "月：" + values[2] + "," + values[3]), new IntWritable(Integer.parseInt(values[11])));
				}
			}
		}
	}

	//统计某个月各个市汽车销量
	public static class CityMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] values = value.toString().trim().split("\t");
			if (values.length > 1) {
				String city = values[0].split(",")[0];
				if (city != null) {
					context.write(new Text(city), new IntWritable(Integer.parseInt(values[1])));
				}
			}
		}
	}

}
