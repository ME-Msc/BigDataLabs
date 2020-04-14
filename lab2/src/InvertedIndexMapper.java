import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text>
{
    @Override
    protected void map(LongWritable key, Text value, Context context)
    // default RecordReader: LineRecordReader
    // key: line offset; value: line string
    throws IOException, InterruptedException
    {
        FileSplit fileSplit = (FileSplit)context.getInputSplit();
        String fileName = fileSplit.getPath().getName();
        Text word = new Text();
        Text fileName_lineOffset = new Text(fileName+"#"+key.toString());
        StringTokenizer itr = new StringTokenizer(value.toString());
        while(itr.hasMoreTokens())
        {
            word.set(itr.nextToken());
            context.write(word, fileName_lineOffset);
        }
    }
}