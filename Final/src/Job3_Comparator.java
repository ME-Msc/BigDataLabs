import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Job3_Comparator extends WritableComparator
{
	protected Job3_Comparator()
	{
		super(Text.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b)
	{
		Text ta = (Text) a;
		Text tb = (Text) b;
		String a_first = ta.toString().split(",")[0];
		String b_first = tb.toString().split(",")[0];
		return a_first.compareTo(b_first);
	}
}
