import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class tropcomp {

   static void main(String[] args) throws Exception {
        if (args.length < 2 || args.length > 4) {
            System.out.println("Usage: tropcomp [-o <output-path.csv>] <input-path> <threshold>");
            return;
        }

        String outputPath = null;
        String inputPath;
        double threshold;

        if (args.length == 4 && "-o".equals(args[0])) {
            outputPath = args[1];
            inputPath = args[2];
            threshold = Double.parseDouble(args[3]);
        } else {
            inputPath = args[0];
            threshold = Double.parseDouble(args[1]);
        }

        File root = new File(inputPath);

        if (!root.exists()) {
            System.out.println("The specified input path doesn't exist.");
            return;
        }

        List<ClassMetrics> metricsList = new ArrayList<>();

        // Calculate metrics for all test classes
        computeMetrics(root, metricsList);

        metricsList.sort(Comparator.comparingDouble(ClassMetrics::getTcmp));

        int index = (int) (threshold / 100.0 * metricsList.size());
        double tlocThreshold = metricsList.get(index).getTloc();
        double tcmpThreshold = metricsList.get(index).getTcmp();

        List<ClassMetrics> suspiciousClasses = new ArrayList<>();

        for (ClassMetrics metric : metricsList) {
            if (metric.getTloc() > tlocThreshold && metric.getTcmp() > tcmpThreshold) {
                suspiciousClasses.add(metric);
            }
        }

       if (outputPath != null) {
           try (PrintStream out = new PrintStream(new FileOutputStream(outputPath))) {
               for (ClassMetrics metric : suspiciousClasses) {
                   out.println(metric); // This line should print the metric in CSV format
               }
           }
       } else {
           for (ClassMetrics metric : suspiciousClasses) {
               System.out.println(metric);
           }
       }

   }

    private static void computeMetrics(File dir, List<ClassMetrics> metricsList) throws Exception {
        if (dir.isDirectory()) {
            for (File child : dir.listFiles()) {
                computeMetrics(child, metricsList);
            }
        } else if (dir.getName().endsWith(".java")) {
            tls tlsInstance = new tls();
            ClassMetrics metrics = tlsInstance.computeMetrics(dir);
            if (metrics != null) {
                metricsList.add(metrics);
            }
        }
    }

    static class ClassMetrics {
        String path;
        String packageName;
        String className;
        double tloc;
        double tassert;
        double tcmp;

        public double getTcmp() {
            if (tassert == 0) {
                return 0;
            }
            return tloc / tassert;
        }

        public double getTloc() {
            return tloc;
        }

        @Override
        public String toString() {
            return path + "    " + packageName + "    " + className + "    " + tloc + "    " + tassert + "    " + getTcmp();
        }
    }
}
