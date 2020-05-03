import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SecondSort {
    public SecondSort() {
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        if (args.length != 2) {
            System.err.println("Usage: InvertedIndex <in> <out>");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "InvertedIndex");
        job.setJarByClass(SecondSort.class);
        job.setMapperClass(SecondSortMapper.class);
        job.setGroupingComparatorClass(SecondSortComparator.class);
        job.setReducerClass(SecondSortReducer.class);
        job.setMapOutputKeyClass(MyKey.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
