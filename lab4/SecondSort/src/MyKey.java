import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class MyKey implements WritableComparable<MyKey> {
    private int first = 0;
    private int second = 0;

    public MyKey() {
    }

    public void set(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }

    public int compareTo(MyKey o) {
        if (this.first != o.first) {
            return this.first - o.first;
        } else {
            return this.second != o.second ? o.second - this.second : 0;
        }
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.first);
        dataOutput.writeInt(this.second);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.first = dataInput.readInt();
        this.second = dataInput.readInt();
    }
}
