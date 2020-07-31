import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Job5_GraphBuilder_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 3)
		{
			System.err.println("Job5-GraphBuilder: <job3_out> <labelled_data> <out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job5_1 = Job.getInstance(conf, "Job5_GraphBuilder");
		job5_1.setJarByClass(Job5_GraphBuilder_Driver.class);
		job5_1.addCacheFile(new Path(args[1]).toUri());
		job5_1.setInputFormatClass(KeyValueTextInputFormat.class);
		job5_1.setMapperClass(Job5_GraphBuilder_Mapper.class);
		job5_1.setMapOutputKeyClass(Text.class);
		job5_1.setMapOutputValueClass(Text.class);
		job5_1.setOutputKeyClass(Text.class);
		job5_1.setOutputValueClass(Text.class);
		job5_1.setNumReduceTasks(0);
		FileInputFormat.addInputPath(job5_1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job5_1, new Path(args[2]));
		job5_1.waitForCompletion(true);
	}
}
