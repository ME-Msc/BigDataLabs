import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InvertedIndexMapper extends Mapper<Object, Text, Text, IntWritable>
{
    @Override
    protected void map(Object key, Text value, Context context)
    // default RecordReader: LineRecordReader
    // key: line offset; value: line string
    throws IOException, InterruptedException
    {
        FileSplit fileSplit = (FileSplit)context.getInputSplit();
        String fileName = fileSplit.getPath().getName();
        int pos = fileName.indexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }

        Text word = new Text();
        StringTokenizer itr = new StringTokenizer(value.toString());
        IntWritable one = new IntWritable(1);
        while(itr.hasMoreTokens()) {
            word.set(itr.nextToken() + "#" + fileName);
            context.write(word, one);
        }
    }
}