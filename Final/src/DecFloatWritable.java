import org.apache.hadoop.io.FloatWritable;;

public class DecFloatWritable extends FloatWritable
{
	public DecFloatWritable()
	{
		super();
	}

	public DecFloatWritable(float f)
	{
		super();
		super.set(f);
	}

	@Override
	public int compareTo(FloatWritable f)
	{
		return -super.compareTo(f);
	}
}
