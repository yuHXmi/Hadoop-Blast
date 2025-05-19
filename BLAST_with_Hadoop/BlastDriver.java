import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class BlastDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] paths = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (paths.length != 3) {
            System.err.println("Usage: BlastDriver <query.fasta> <output> <blast_db_path>");
            System.exit(2);
        }

        conf.set("blast.db.path", paths[2]);

        Job job = Job.getInstance(conf, "Hadoop BLAST");
        job.setJarByClass(BlastDriver.class);
        job.setMapperClass(BlastMapper.class);
        job.setReducerClass(BlastReducer.class);
        job.setInputFormatClass(FastaInputFormat.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FastaInputFormat.addInputPath(job, new Path(paths[0]));
        FileOutputFormat.setOutputPath(job, new Path(paths[1]));

        long start = System.currentTimeMillis();
        boolean success = job.waitForCompletion(true);
        long end = System.currentTimeMillis();
        System.out.println("Job runtime: " + (end - start) + " ms");

        System.exit(success ? 0 : 1);
    }
}
