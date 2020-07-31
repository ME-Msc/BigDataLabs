import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class Job4_PageRankIter_Partitioner extends HashPartitioner<PageRankBean, NullWritable>
{
	@Override
	public int getPartition(PageRankBean key, NullWritable value, int numReduceTasks)
	{
		return (key.getName().hashCode() & Integer.MAX_VALUE) % numReduceTasks;
	}
}
