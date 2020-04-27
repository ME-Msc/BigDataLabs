import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<OrderBean,NullWritable,Text,NullWritable>{
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context)throws IOException,InterruptedException
    {
        Iterator<NullWritable> it=values.iterator();
        it.next();
        String name=key.getPname();
        String price=key.getPrice();
        while(it.hasNext())
        {
            it.next();
            key.setPname(name);
            key.setPrice(price);
            Text outputKey = new Text();
            outputKey.set(    key.getOid() + "\t"
                            + key.getOdate() + "\t"
                            + key.getPid() + "\t"
                            + key.getPname() + "\t"
                            + key.getPrice() + "\t"
                            + key.getOamount() + "\t");
            context.write(outputKey, NullWritable.get());
        }
    }
}
