import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class Job1_Mapper extends Mapper<LongWritable, Text, Text, NullWritable>
{
	private String filename;
	private List<String> people_list = new ArrayList<>();

	@Override
	public void setup(Context context) throws IOException, InterruptedException
	// override setup()
	{
		FileSplit fs = (FileSplit) context.getInputSplit();
		filename = fs.getPath().getName(); // 获取输入的文件名

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
						people_list.add(line);
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
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String author = filename.split("[0-9]{2}")[0];
		if (!author.equals("金庸"))
			return;

		String names = new String();
		StringTokenizer itr = new StringTokenizer(value.toString());
		boolean hasName = false;
		while (itr.hasMoreTokens())
		{
			String word = itr.nextToken();
			if (people_list.contains(word))
			{
				names = names + word + " ";
				hasName = true;
			}
		}
		if (hasName)
		{
			names = names.substring(0, names.length() - 1);
			context.write(new Text(names), NullWritable.get());
		}
	}
}
