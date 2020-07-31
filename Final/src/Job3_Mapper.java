import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job3_Mapper extends Mapper<Text, Text, Text, IntWritable>
{
	@Override
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	{
		IntWritable newvalue = new IntWritable(Integer.parseInt(value.toString()));
		context.write(key, newvalue);
	}
}
