import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job4_GraphBuilder_Mapper extends Mapper<Text, Text, Text, Text>
{
	@Override
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	{
		String newvalue = Job4_Driver.InitValue + "#" + value.toString();
		context.write(key, new Text(newvalue));
	}
}
