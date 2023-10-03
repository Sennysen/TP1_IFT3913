import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tls {

    public static void main(String[] args) throws Exception {
        if (args.length < 1 || (args.length > 1 && !"-o".equals(args[0]))) {
            System.out.println("Usage: tls [-o <path-to-output.csv>] <path-to-input>");
            return;
        }

        String outputPath = args.length == 3 ? args[1] : null;
        String inputPath = args[args.length - 1];

        File root = new File(inputPath);
        if (!root.exists()) {
            System.out.println("The specified input path doesn't exist.");
            return;
        }

        // Compute metrics
        List<ClassMetrics> metricsList = new ArrayList<>();
        computeMetrics(root, metricsList);

        // Print metrics
        for (ClassMetrics metric : metricsList) {
            String line = metric.toCSV();
            if (outputPath != null) {
                // TODO: Write line to the CSV file specified in outputPath
            } else {
                System.out.println(line);
            }
        }
    }

    static void computeMetrics(File dir, List<ClassMetrics> metricsList) throws Exception {
        if (dir.isDirectory()) {
            for (File child : dir.listFiles()) {
                computeMetrics(child, metricsList);
            }
        } else if (dir.getName().endsWith(".java")) {
            ClassMetrics metrics = extractMetricsFromFile(dir);
            if (metrics != null) {
                metricsList.add(metrics);
            }
        }
    }

    private static ClassMetrics extractMetricsFromFile(File file) {
        ClassMetrics metrics = new ClassMetrics();
        metrics.path = file.getPath();

        // Initialize metrics
        metrics.tloc = 0;
        metrics.tassert = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                metrics.tloc++;  // Increment TLOC for every line

                if (line.contains("package ")) {
                    metrics.packageName = line.replace("package ", "").replace(";", "").trim();
                } else if (line.contains("class ")) {
                    metrics.className = line.split(" ")[line.split(" ").length - 1].replace("{", "").trim();
                } else if (line.contains("assert")) {
                    metrics.tassert++;  // Increment TASSERT for every assertion found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return metrics;
    }


    static class ClassMetrics {
        String path;
        String packageName;
        String className;
        double tloc;
        double tassert;

        public double getTcmp() {
            return tassert == 0 ? 0 : tloc / tassert;
        }

        public String toCSV() {
            return path + "," + packageName + "," + className + "," + tloc + "," + tassert + "," + getTcmp();
        }

        public double getTloc() {
            return tloc;
        }
    }
}
