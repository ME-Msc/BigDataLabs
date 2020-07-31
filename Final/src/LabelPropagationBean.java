import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class LabelPropagationBean implements WritableComparable<LabelPropagationBean>
{
	private String name;
	private int label_id;
	private float prob;

	public LabelPropagationBean()
	{
		super();
		this.name = "";
		this.label_id = -1;
		this.prob = 0;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setLabel(int label_id)
	{
		this.label_id = label_id;
	}

	public int getLabel()
	{
		return this.label_id;
	}

	public void setProb(float prob)
	{
		this.prob = prob;
	}

	public float getProb()
	{
		return this.prob;
	}

	@Override
	public int compareTo(LabelPropagationBean lp)
	{
		return this.name.compareTo(lp.name);
	}

	@Override
	public void write(DataOutput dataOutput) throws IOException
	{
		dataOutput.writeUTF(name);
		dataOutput.writeInt(label_id);
		dataOutput.writeFloat(prob);
	}

	@Override
	public void readFields(DataInput dataInput) throws IOException
	{
		this.name = dataInput.readUTF();
		this.label_id = dataInput.readInt();
		this.prob = dataInput.readFloat();
	}
}
