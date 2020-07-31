import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Job4_PageRankIter_Reducer extends Reducer<PageRankBean, NullWritable, Text, Text>
{
	@Override
	public void reduce(PageRankBean key, Iterable<NullWritable> values, Context context)
			throws IOException, InterruptedException
	{
		String newkey = "", linklist = "";
		float cur_rank = 0;
		Iterator<NullWritable> iter = values.iterator();
		while (iter.hasNext())
		{
			iter.next();
			newkey = key.getName();
			if (key.getRank() == -1)
			{
				linklist = key.getLinklist();
			}
			else
			{
				cur_rank += key.getRank();
			}
		}
		float d = 0.85f;
		cur_rank = d * cur_rank + (1 - d) * Job4_Driver.InitValue;
		String newvalue = Float.toString(cur_rank) + "#" + linklist;
		context.write(new Text(newkey), new Text(newvalue));
	}
}
