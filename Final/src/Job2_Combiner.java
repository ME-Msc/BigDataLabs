import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Job2_Combiner extends Reducer<Text, IntWritable, Text, IntWritable>
{
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		int sum = 0;
		Iterator<IntWritable> iter = values.iterator();
		while (iter.hasNext())
		{
			sum += iter.next().get();
		}
		context.write(key, new IntWritable(sum));
	}
}
