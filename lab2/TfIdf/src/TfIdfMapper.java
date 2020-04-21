import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TfIdfMapper extends Mapper<Object, Text, Text, Text>{
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String []termFreq = value.toString().split(",")[1].split(";");
        int fileNumbers = Integer.parseInt(context.getConfiguration().get("fileNumbers"));
        double idf = Math.log((double)fileNumbers/(termFreq.length + 1));
        Map<String, Integer> authorMap = new HashMap<>();
        for(String term:termFreq) {
            String authorNumArticle = term.split(":")[0];
            String author = "";
            for (int i = 0; i < authorNumArticle.length(); ++i) {
                if (authorNumArticle.charAt(i) < '0' || authorNumArticle.charAt(i) > '9')
                    author = author + authorNumArticle.substring(i, i + 1);
                else
                    break;
            }
            int freq = Integer.parseInt(term.split(":")[1]);
            if(authorMap.containsKey(author))
                authorMap.put(author, authorMap.get(author)+freq);
            else
                authorMap.put(author, freq);
        }
        for(Map.Entry<String, Integer> entry:authorMap.entrySet()) {
            Text name = new Text();
            name.set(entry.getKey());
            if(!entry.getKey().equals(""))
                context.write(name, new Text(key + "#" + String.format("%.02f", entry.getValue()*idf)));
        }

    }
}
