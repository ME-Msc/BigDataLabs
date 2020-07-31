import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Job4_PageRankIter_Comparator extends WritableComparator
{
	protected Job4_PageRankIter_Comparator()
	{
		super(PageRankBean.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b)
	{
		PageRankBean pa = (PageRankBean) a;
		PageRankBean pb = (PageRankBean) b;
		return pa.getName().compareTo(pb.getName());
	}
}
