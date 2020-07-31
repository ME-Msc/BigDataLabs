import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job4_PageRankViewer_Mapper extends Mapper<Text, Text, DecFloatWritable, Text>
{
	@Override
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	{
		DecFloatWritable newkey = new DecFloatWritable(Float.parseFloat(value.toString().split("#")[0]));
		context.write(newkey, key);
	}
}
