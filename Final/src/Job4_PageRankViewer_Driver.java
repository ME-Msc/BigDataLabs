import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job4_PageRankViewer_Driver /user/2020st39/Final_out/Job4_out/DataXX /user/2020st39/Final_out/Job4_out/FinalRankXX
*/

public class Job4_PageRankViewer_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 2)
		{
			System.err.println("Job4-PageRankViewer: <in> <out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job4_3 = Job.getInstance(conf, "Job4_PageRankViewer");
		job4_3.setJarByClass(Job4_PageRankViewer_Driver.class);
		job4_3.setInputFormatClass(KeyValueTextInputFormat.class);
		job4_3.setMapperClass(Job4_PageRankViewer_Mapper.class);
		job4_3.setMapOutputKeyClass(DecFloatWritable.class);
		job4_3.setMapOutputValueClass(Text.class);
		job4_3.setOutputKeyClass(DecFloatWritable.class);
		job4_3.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job4_3, new Path(args[0]));
		FileOutputFormat.setOutputPath(job4_3, new Path(args[1]));
		job4_3.waitForCompletion(true);
	}
}
