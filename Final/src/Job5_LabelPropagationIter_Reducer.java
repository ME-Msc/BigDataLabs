import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Job5_LabelPropagationIter_Reducer extends Reducer<LabelPropagationBean, NullWritable, Text, Text>
{
	Map<String, Integer> labels = new HashMap<String, Integer>();

	@Override
	public void setup(Context context) throws IOException, InterruptedException
	{
		try
		{
			Path[] cacheFiles = context.getLocalCacheFiles();
			if (cacheFiles != null && cacheFiles.length > 0)
			{
				String line;
				BufferedReader fileReader = new BufferedReader(new FileReader(cacheFiles[1].toString()));
				try
				{
					while ((line = fileReader.readLine()) != null)
					{
						String name = line.split(" ")[0];
						int label_id = Integer.parseInt(line.split(" ")[1]);
						labels.put(name, label_id);
					}
				}
				finally
				{
					fileReader.close();
				}
			}
		}
		catch (IOException e)
		{
			System.err.println("Exception reading DistributedCache: " + e);
		}
	}

	@Override
	public void reduce(LabelPropagationBean key, Iterable<NullWritable> values, Context context)
			throws IOException, InterruptedException
	{
		String newkey = "", newvalue = "";
		float[] cur_prob = new float[Job5_Driver.label_num];
		Iterator<NullWritable> iter = values.iterator();
		while (iter.hasNext())
		{
			iter.next();
			newkey = key.getName();
			cur_prob[key.getLabel()] += key.getProb();
		}

		if (labels.get(newkey) != null)
		{
			for (int i = 0; i < Job5_Driver.label_num; i++)
			{
				cur_prob[i] = 0;
			}
			cur_prob[labels.get(newkey)] = 10000;
		}

		for (int i = 0; i < Job5_Driver.label_num; i++)
		{
			newvalue = newvalue + Integer.toString(i) + ":" + Float.toString(cur_prob[i]) + ",";
		}
		newvalue = newvalue.substring(0, newvalue.length() - 1);
		context.write(new Text(newkey), new Text(newvalue));
	}
}
