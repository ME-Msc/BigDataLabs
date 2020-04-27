import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private String oid;
    private String odate;
    private String pid;
    private String pname;
    private String price;
    private String oamount;

    public OrderBean() {
        super();
        this.oid = "";
        this.odate = "";
        this.pid = "";
        this.pname = "";
        this.price = "";
        this.oamount = "";
    }

    public void setOid(String s)
    {
        this.oid=s;
    }
    public String getOid()
    {
        return this.oid;
    }

    public void setOdate(String s)
    {
        this.odate=s;
    }
    public String getOdate()
    {
        return this.odate;
    }

    public void setPid(String s)
    {
        this.pid=s;
    }
    public String getPid()
    {
        return this.pid;
    }

    public void setPname(String s)
    {
        this.pname=s;
    }
    public String getPname()
    {
        return this.pname;
    }

    public void setPrice(String s)
    {
        this.price=s;
    }
    public String getPrice()
    {
        return this.price;
    }

    public void setOamount(String s)
    {
        this.oamount=s;
    }
    public String getOamount()
    {
        return this.oamount;
    }


    @Override
    public int compareTo(OrderBean o) {
        int compare=this.pid.compareTo(o.pid);
        if(compare==0)
            return o.pname.compareTo(this.pname);
        else
            return compare;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(oid);
        dataOutput.writeUTF(odate);
        dataOutput.writeUTF(pid);
        dataOutput.writeUTF(pname);
        dataOutput.writeUTF(price);
        dataOutput.writeUTF(oamount);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.oid = dataInput.readUTF();
        this.odate = dataInput.readUTF();
        this.pid = dataInput.readUTF();
        this.pname = dataInput.readUTF();
        this.price = dataInput.readUTF();
        this.oamount = dataInput.readUTF();
    }


}
