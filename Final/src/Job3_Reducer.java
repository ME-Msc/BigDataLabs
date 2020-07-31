import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Job3_Reducer extends Reducer<Text, IntWritable, Text, Text>
{
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		List<String> keylist=new ArrayList<String>();
		List<Integer> valuelist=new ArrayList<Integer>();

		int sum = 0;
		Iterator<IntWritable> iter1 = values.iterator();
		while (iter1.hasNext())
		{
			int cur = iter1.next().get();
			keylist.add(key.toString());
			valuelist.add(cur);
			sum += cur;
		}

		String newkey = keylist.get(0).split(",")[0];
		String newvalue = new String();
		for(int i = 0; i < keylist.size(); i++)
		{
			float prob = valuelist.get(i) / (float)sum;
			newvalue = newvalue + keylist.get(i).split(",")[1] + "," + Float.toString(prob) + "|";
		}
		newvalue = newvalue.substring(0, newvalue.length() - 1);
		context.write(new Text(newkey), new Text(newvalue));
	}
}
