import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job5_GraphBuilder_Mapper extends Mapper<Text, Text, Text, Text>
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
				BufferedReader fileReader = new BufferedReader(new FileReader(cacheFiles[0].toString()));
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
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException
	{
		String name = key.toString();
		int[] init_value = new int[Job5_Driver.label_num];
		if (labels.get(name) != null)
		{
			init_value[labels.get(name)] = 10000;
		}

		String newvalue = new String();
		for (int i = 0; i < Job5_Driver.label_num; i++)
		{
			newvalue = newvalue + Integer.toString(i) + ":" + Integer.toString(init_value[i]) + ",";
		}
		newvalue = newvalue.substring(0, newvalue.length() - 1);
		context.write(key, new Text(newvalue));
	}
}
