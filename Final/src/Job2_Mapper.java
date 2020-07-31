import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job2_Mapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String[] temp_names = value.toString().split(" ");
		List<String> names = new ArrayList<>();
		for (int i = 0; i < temp_names.length; i++)
		{
			if (!names.contains(temp_names[i]))
			{
				names.add(temp_names[i]);
			}
		}
		if (names.size() < 2)
			return;
		IntWritable one = new IntWritable(1);
		for (int i = 0; i < names.size(); i++)
		{
			for (int j = i + 1; j < names.size(); j++)
			{
				context.write(new Text(names.get(i) + "," + names.get(j)), one);
				context.write(new Text(names.get(j) + "," + names.get(i)), one);
			}
		}
	}
}
