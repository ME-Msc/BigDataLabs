import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class PageRankBean implements WritableComparable<PageRankBean>
{
	private String name;
	private float rank;
	private String linklist;

	public PageRankBean()
	{
		super();
		this.name = "";
		this.rank = -1;
		this.linklist = "";
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setRank(float rank)
	{
		this.rank = rank;
	}

	public float getRank()
	{
		return this.rank;
	}

	public void setLinklist(String linklist)
	{
		this.linklist = linklist;
	}

	public String getLinklist()
	{
		return this.linklist;
	}

	@Override
	public int compareTo(PageRankBean pr)
	{
		return this.name.compareTo(pr.name);
	}

	@Override
	public void write(DataOutput dataOutput) throws IOException
	{
		dataOutput.writeUTF(name);
		dataOutput.writeFloat(rank);
		dataOutput.writeUTF(linklist);
	}

	@Override
	public void readFields(DataInput dataInput) throws IOException
	{
		this.name = dataInput.readUTF();
		this.rank = dataInput.readFloat();
		this.linklist = dataInput.readUTF();
	}
}
