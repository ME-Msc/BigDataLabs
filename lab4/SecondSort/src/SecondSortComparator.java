import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondSortComparator extends WritableComparator {
    public SecondSortComparator() {
        super(MyKey.class, true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        MyKey mykey1 = (MyKey)a;
        MyKey mykey2 = (MyKey)b;
        return mykey1.getFirst() - mykey2.getFirst();
    }
}