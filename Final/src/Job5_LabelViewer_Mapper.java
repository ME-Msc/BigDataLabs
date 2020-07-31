import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Job5_LabelViewer_Mapper extends Mapper<Text, Text, IntWritable, Text>
{
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

		int max_id = 0;
		float max = 0;
		for (int i = 0; i < Job5_Driver.label_num; i++)
		{
			if (cur_prob[i] > max)
			{
				max = cur_prob[i];
				max_id = i;
			}
		}
		context.write(new IntWritable(max_id), key);
	}
}
