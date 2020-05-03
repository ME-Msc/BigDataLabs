import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SecondSortMapper extends Mapper<LongWritable, Text, MyKey, IntWritable> {
    private final MyKey mykey = new MyKey();
    private final IntWritable myvalue = new IntWritable();

    public SecondSortMapper() {
    }

    public void map(LongWritable key, Text value, Mapper<LongWritable, Text, MyKey, IntWritable>.Context context) throws IOException, InterruptedException {
        String[] str = value.toString().split("\t");
        this.mykey.set(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        this.myvalue.set(Integer.parseInt(str[1]));
        context.write(this.mykey, this.myvalue);
    }
}
