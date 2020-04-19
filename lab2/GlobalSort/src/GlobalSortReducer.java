import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GlobalSortReducer extends Reducer<FloatWritable, Text, Text, Text> {
    protected void reduce(FloatWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator itr = values.iterator();

        while(itr.hasNext()) {
            String tmp = ((Text)itr.next()).toString();
            String[] buf = tmp.split("#");
            context.write(new Text(buf[0]), new Text(buf[1]));
        }

    }
}
