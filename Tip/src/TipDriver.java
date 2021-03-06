package tip;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TipDriver {
    public static void TipProfiling(String[] args) throws Exception {
        System.out.println("Profile:");
        Job job = Job.getInstance();
        job.setJarByClass(Tip.class);
        job.setJobName("Profile");
        job.setNumReduceTasks(1);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(tip.TipMapper.class);
        job.setCombinerClass(tip.TipReducer.class);
        job.setReducerClass(tip.TipReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(tip.TipSet.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}