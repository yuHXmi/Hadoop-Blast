import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import java.io.IOException;

public class FastaInputFormat extends FileInputFormat<Text, Text> {
    @Override
    public RecordReader<Text, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        return new FastaRecordReader();
    }

    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return false; // giữ toàn bộ query trong một phần cho mỗi mapper
    }
}
