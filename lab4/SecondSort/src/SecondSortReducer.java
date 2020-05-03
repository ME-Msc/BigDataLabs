import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SecondSortReducer extends Reducer<MyKey, IntWritable, Text, IntWritable> {
    private final Text first = new Text();

    public SecondSortReducer() {
    }

    public void reduce(MyKey key, Iterable<IntWritable> values, Reducer<MyKey, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        this.first.set(Integer.toString(key.getFirst()));
        Iterator var4 = values.iterator();

        while(var4.hasNext()) {
            IntWritable value = (IntWritable)var4.next();
            context.write(this.first, value);
        }

    }
}