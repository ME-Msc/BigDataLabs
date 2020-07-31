import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job5_LabelPropagationIter_Mapper extends Mapper<Text, Text, LabelPropagationBean, NullWritable>
{
	HashMap<String, HashMap<String, Float>> links = new HashMap<String, HashMap<String, Float>>();

	@Override
	public void setup(Context context) throws IOException, InterruptedException
	{
		try
		{
			Path[] cacheFiles = context.getLocalCacheFiles();
			if (cacheFiles != null && cacheFiles.length > 0)
			{
				if (cacheFiles.length != 2)
				{
					throw new IOException("Number of DistributedCache is wrong!");
				}

				String line;
				BufferedReader fileReader = new BufferedReader(new FileReader(cacheFiles[0].toString()));
				try
				{
					while ((line = fileReader.readLine()) != null)
					{
						HashMap<String, Float> weight = new HashMap<String, Float>();
						String from = line.split("\t")[0];
						String[] to = line.split("\t")[1].split("\\|");
						for (int i = 0; i < to.length; i++)
						{
							String toName = to[i].split(",")[0];
							float prob = Float.parseFloat(to[i].split(",")[1]);
							weight.put(toName, prob);
						}
						links.put(from, weight);
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
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	{
		float[] cur_prob = new float[Job5_Driver.label_num];
		String[] probs = value.toString().split(",");
		for (int i = 0; i < probs.length; i++)
		{
			int label_id = Integer.parseInt(probs[i].split(":")[0]);
			float prob = Float.parseFloat(probs[i].split(":")[1]);
			cur_prob[label_id] = prob;
		}

		String name = key.toString();
		HashMap<String, Float> weight = links.get(name);
		for (int i = 0; i < Job5_Driver.label_num; i++)
		{
			for (Map.Entry<String, Float> entry : weight.entrySet())
			{
				LabelPropagationBean lp = new LabelPropagationBean();
				lp.setName(entry.getKey());
				lp.setLabel(i);
				lp.setProb(cur_prob[i] * entry.getValue());
				context.write(lp, NullWritable.get());
			}
		}
	}
}
