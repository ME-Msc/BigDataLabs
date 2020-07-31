import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job4_PageRankIter_Driver /user/2020st39/Final_out/Job4_out/DataXX /user/2020st39/Final_out/Job4_out/DataXX cur_time
*/

public class Job4_PageRankIter_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 3)
		{
			System.err.println("Job4-PageRankIter: <in> <out> <cur_time>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job4_2 = Job.getInstance(conf, "Job4_PageRankIter_" + args[2]);
		job4_2.setJarByClass(Job4_PageRankIter_Driver.class);
		job4_2.setInputFormatClass(KeyValueTextInputFormat.class);
		job4_2.setMapperClass(Job4_PageRankIter_Mapper.class);
		job4_2.setPartitionerClass(Job4_PageRankIter_Partitioner.class);
		job4_2.setGroupingComparatorClass(Job4_PageRankIter_Comparator.class);
		job4_2.setReducerClass(Job4_PageRankIter_Reducer.class);
		job4_2.setMapOutputKeyClass(PageRankBean.class);
		job4_2.setMapOutputValueClass(NullWritable.class);
		job4_2.setOutputKeyClass(Text.class);
		job4_2.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job4_2, new Path(args[0]));
		FileOutputFormat.setOutputPath(job4_2, new Path(args[1]));
		job4_2.waitForCompletion(true);
	}
}
