import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Job1_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 3)
		{
			System.err.println("Job1: <novels> <people_list> <job1_out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job1 = Job.getInstance(conf, "Job1");
		job1.setJarByClass(Job1_Driver.class);
		job1.addCacheFile(new Path(args[1]).toUri());
		job1.setMapperClass(Job1_Mapper.class);
		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(NullWritable.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(NullWritable.class);
		FileInputFormat.addInputPath(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[2]));
		job1.waitForCompletion(true);
	}
}
