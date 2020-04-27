import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class JoinMapper extends Mapper<Object,Text,OrderBean, NullWritable>
{

    private String filename;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fs = (FileSplit) context.getInputSplit();
        filename = fs.getPath().getName();
    }

    @Override
    protected void map(Object key, Text value, Context context)throws IOException,InterruptedException
    {
        String[] fields=value.toString().split(" ");
        OrderBean ob = new OrderBean();
        if(filename.equals("product.txt"))
        {
            ob.setPid(fields[0]);
            ob.setPname(fields[1]);
            ob.setPrice(fields[2]);
        }
        else
        {
            ob.setOid(fields[0]);
            ob.setOdate(fields[1]);
            ob.setPid(fields[2]);
            ob.setOamount(fields[3]);
        }
        context.write(ob,NullWritable.get());
    }
}
