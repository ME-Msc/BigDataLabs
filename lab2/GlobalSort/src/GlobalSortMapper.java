import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GlobalSortMapper extends Mapper<Object, Text, FloatWritable, Text> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String num = value.toString().split(",")[0];
        FloatWritable newKey = new FloatWritable(Float.parseFloat(num));
        String tmpValue = key.toString() + "#" + value.toString();
        context.write(newKey, new Text(tmpValue));
    }
}
