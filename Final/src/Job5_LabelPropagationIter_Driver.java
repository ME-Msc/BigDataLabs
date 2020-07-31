import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Job5_LabelPropagationIter_Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 5)
		{
			System.err.println("Job5-LabelPropagationIter: <in> <out> <link_matrix> <labelled_data> <cur_time>");
			System.exit(2);
		}

		Configuration conf = new Configuration();
		Job job5_2 = Job.getInstance(conf, "Job5_LabelPropagationIter_" + args[4]);
		job5_2.setJarByClass(Job5_LabelPropagationIter_Driver.class);
		job5_2.addCacheFile(new Path(args[2]).toUri());
		job5_2.addCacheFile(new Path(args[3]).toUri());
		job5_2.setInputFormatClass(KeyValueTextInputFormat.class);
		job5_2.setMapperClass(Job5_LabelPropagationIter_Mapper.class);
		job5_2.setPartitionerClass(Job5_LabelPropagationIter_Partitioner.class);
		job5_2.setGroupingComparatorClass(Job5_LabelPropagationIter_Comparator.class);
		job5_2.setReducerClass(Job5_LabelPropagationIter_Reducer.class);
		job5_2.setMapOutputKeyClass(LabelPropagationBean.class);
		job5_2.setMapOutputValueClass(NullWritable.class);
		job5_2.setOutputKeyClass(Text.class);
		job5_2.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job5_2, new Path(args[0]));
		FileOutputFormat.setOutputPath(job5_2, new Path(args[1]));
		job5_2.waitForCompletion(true);
	}
}
