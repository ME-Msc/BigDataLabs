import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends Reducer<Text, IntWritable, Text, Text> {
    // setup
    private String term = new String();
    private String termPrev = " ";
    private StringBuilder postingList = new StringBuilder();
    private int countWord = 0;
    private int countDoc = 0;
    private float frequency = 0;

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        this.term = key.toString().split("#")[0];
        if (!this.term.equals(this.termPrev)) {
            if (!this.termPrev.equals(" ")) {
                this.postingList.setLength(this.postingList.length() - 1);
                this.frequency = (float)this.countWord / (float)this.countDoc;
                context.write(new Text(this.termPrev), new Text(String.format("%.2f,%s", this.frequency, this.postingList.toString())));
                this.countWord = 0;
                this.countDoc = 0;
                this.postingList = new StringBuilder();
            }

            this.termPrev = this.term;
        }

        int sum = 0;
        Iterator<IntWritable> it = values.iterator();
        while (it.hasNext()) {
            sum += it.next().get();
        }

        this.postingList.append(key.toString().split("#")[1] + ":" + sum + ";");
        this.countWord += sum;
        ++this.countDoc;
    }

    @Override
    public void cleanup(Context context) throws IOException, InterruptedException {
        this.postingList.setLength(this.postingList.length() - 1);
        this.frequency = (float)this.countWord / (float)this.countDoc;
        context.write(new Text(this.termPrev), new Text(String.format("%.2f,%s", this.frequency, this.postingList.toString())));
    }
}