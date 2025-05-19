import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.*;
import java.util.*;

public class FastaRecordReader extends RecordReader<Text, Text> {
    private BufferedReader reader;
    private Text key = new Text();
    private Text value = new Text();
    private boolean hasMore = true;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException {
        Path path = ((FileSplit) inputSplit).getPath();
        FileSystem fs = path.getFileSystem(context.getConfiguration());
        FSDataInputStream fileIn = fs.open(path);
        reader = new BufferedReader(new InputStreamReader(fileIn));
    }

    @Override
    public boolean nextKeyValue() throws IOException {
        String line;
        String header = null;
        StringBuilder sequence = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith(">")) {
                if (header != null) {
                    key.set(header);
                    value.set(sequence.toString());
                    return true;
                }
                header = line;
                sequence.setLength(0);
            } else {
                sequence.append(line.trim());
            }
        }

        if (header != null) {
            key.set(header);
            value.set(sequence.toString());
            hasMore = false;
            return true;
        }

        return false;
    }

    @Override
    public Text getCurrentKey() {
        return key;
    }

    @Override
    public Text getCurrentValue() {
        return value;
    }

    @Override
    public float getProgress() {
        return hasMore ? 0.5f : 1.0f;
    }

    @Override
    public void close() throws IOException {
        if (reader != null) reader.close();
    }
}
