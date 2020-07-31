import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job4_PageRankIter_Mapper extends Mapper<Text, Text, PageRankBean, NullWritable>
{
	@Override
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	{
		PageRankBean pr_linklist = new PageRankBean();
		pr_linklist.setName(key.toString());
		pr_linklist.setLinklist(value.toString().split("#")[1]);
		context.write(pr_linklist, NullWritable.get());

		float cur_rank = Float.parseFloat(value.toString().split("#")[0]);
		String[] linklist = value.toString().split("#")[1].split("\\|");
		for (int i = 0; i < linklist.length; i++)
		{
			String name = linklist[i].split(",")[0];
			float prob = Float.parseFloat(linklist[i].split(",")[1]);

			PageRankBean pr_rank = new PageRankBean();
			pr_rank.setName(name);
			pr_rank.setRank(cur_rank * prob);
			context.write(pr_rank, NullWritable.get());
		}
	}
}
