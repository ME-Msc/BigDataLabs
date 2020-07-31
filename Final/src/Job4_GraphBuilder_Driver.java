import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Job4_GraphBuilder_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 2)
		{
			System.err.println("Job4-GraphBuilder: <in> <out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job4_1 = Job.getInstance(conf, "Job4_GraphBuilder");
		job4_1.setJarByClass(Job4_GraphBuilder_Driver.class);
		job4_1.setInputFormatClass(KeyValueTextInputFormat.class);
		job4_1.setMapperClass(Job4_GraphBuilder_Mapper.class);
		job4_1.setMapOutputKeyClass(Text.class);
		job4_1.setMapOutputValueClass(Text.class);
		job4_1.setOutputKeyClass(Text.class);
		job4_1.setOutputValueClass(Text.class);
		job4_1.setNumReduceTasks(0);
		FileInputFormat.addInputPath(job4_1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job4_1, new Path(args[1]));
		job4_1.waitForCompletion(true);
	}
}
