import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Job5_LabelViewer_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 2)
		{
			System.err.println("Job5-LabelViewer: <in> <out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job5_3 = Job.getInstance(conf, "Job5_LabelViewer");
		job5_3.setJarByClass(Job5_LabelViewer_Driver.class);
		job5_3.setInputFormatClass(KeyValueTextInputFormat.class);
		job5_3.setMapperClass(Job5_LabelViewer_Mapper.class);
		job5_3.setMapOutputKeyClass(IntWritable.class);
		job5_3.setMapOutputValueClass(Text.class);
		job5_3.setOutputKeyClass(IntWritable.class);
		job5_3.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job5_3, new Path(args[0]));
		FileOutputFormat.setOutputPath(job5_3, new Path(args[1]));
		job5_3.waitForCompletion(true);
	}
}
