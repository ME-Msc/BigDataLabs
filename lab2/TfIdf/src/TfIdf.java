import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TfIdf {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        if (args.length != 3) {
            System.err.println("Usage: TfIdf <in> <out> <raw-in>");
            System.exit(2);
        }

        FileSystem fs = FileSystem.get(conf);
        FileStatus []files = fs.listStatus(new Path(args[2]));
        conf.setInt("fileNumbers", files.length);

        Job job = Job.getInstance(conf, "TfIdf");
        job.setJarByClass(TfIdf.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setMapperClass(TfIdfMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
