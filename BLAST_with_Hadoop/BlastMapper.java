import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.*;

public class BlastMapper extends Mapper<Text, Text, Text, Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();

        String query = key.toString() + "\n" + value.toString();
        File tempFile = File.createTempFile("query", ".fasta");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(query);
        }

        String blastDbPath = context.getConfiguration().get("blast.db.path");

        ProcessBuilder pb = new ProcessBuilder(
            "blastp", "-query", tempFile.getAbsolutePath(),
            "-db", blastDbPath, "-outfmt", "6"
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
        }
        process.waitFor();

        context.write(key, new Text(result.toString()));

        long end = System.currentTimeMillis();
        System.out.println("Mapper " + key.toString() + " took " + (end - start) + " ms");
    }
}
