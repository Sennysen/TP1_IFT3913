import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class tropcomp {

    public static void main(String[] args) throws Exception {
        if (args.length < 2 || (args.length > 2 && !"-o".equals(args[0]))) {
            System.out.println("Usage: tropcomp [-o <path-to-output.csv>] <path-to-input> <threshold>");
            return;
        }

        String outputPath = args.length == 4 ? args[1] : null;
        String inputPath = args[args.length - 2];
        double threshold = Double.parseDouble(args[args.length - 1]);

        File root = new File(inputPath);
        if (!root.exists()) {
            System.out.println("The specified input path doesn't exist.");
            return;
        }

        // Compute metrics
        List<tls.ClassMetrics> metricsList = new ArrayList<>();
        tls.computeMetrics(root, metricsList);

        metricsList.sort(Comparator.comparingDouble(tls.ClassMetrics::getTcmp));

        int index = (int) (threshold / 100.0 * metricsList.size());
        double tlocThreshold = metricsList.get(index).getTloc();
        double tcmpThreshold = metricsList.get(index).getTcmp();

        List<tls.ClassMetrics> suspiciousClasses = new ArrayList<>();

        for (tls.ClassMetrics metric : metricsList) {
            if (metric.getTloc() > tlocThreshold && metric.getTcmp() > tcmpThreshold) {
                suspiciousClasses.add(metric);
            }
        }

        // Print suspicious classes
        for (tls.ClassMetrics metric : suspiciousClasses) {
            String line = metric.toCSV();
            if (outputPath != null) {
                try (PrintStream out = new PrintStream(new FileOutputStream(outputPath, true))) { // 'true' for appending
                    out.println(line);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(line);
            }
        }

    }
}
