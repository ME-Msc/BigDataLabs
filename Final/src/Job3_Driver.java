import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Job3_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 2)
		{
			System.err.println("Job3: <job2_out> <job3_out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job3 = Job.getInstance(conf, "Job3");
		job3.setJarByClass(Job3_Driver.class);
		job3.setInputFormatClass(KeyValueTextInputFormat.class);
		job3.setMapperClass(Job3_Mapper.class);
		job3.setPartitionerClass(Job3_Partitioner.class);
		job3.setGroupingComparatorClass(Job3_Comparator.class);
		job3.setReducerClass(Job3_Reducer.class);
		job3.setMapOutputKeyClass(Text.class);
		job3.setMapOutputValueClass(IntWritable.class);
		job3.setOutputKeyClass(Text.class);
		job3.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job3, new Path(args[0]));
		FileOutputFormat.setOutputPath(job3, new Path(args[1]));
		job3.waitForCompletion(true);
	}
}
