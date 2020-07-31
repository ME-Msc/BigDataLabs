import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Job5_LabelPropagationIter_Comparator extends WritableComparator
{
	protected Job5_LabelPropagationIter_Comparator()
	{
		super(LabelPropagationBean.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b)
	{
		LabelPropagationBean la = (LabelPropagationBean) a;
		LabelPropagationBean lb = (LabelPropagationBean) b;
		return la.getName().compareTo(lb.getName());
	}
}
