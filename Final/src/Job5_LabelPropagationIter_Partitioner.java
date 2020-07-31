import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class Job5_LabelPropagationIter_Partitioner extends HashPartitioner<LabelPropagationBean, NullWritable>
{
	@Override
	public int getPartition(LabelPropagationBean key, NullWritable value, int numReduceTasks)
	{
		return (key.getName().hashCode() & Integer.MAX_VALUE) % numReduceTasks;
	}
}
