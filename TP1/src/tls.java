import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tls {

    public tropcomp.ClassMetrics computeMetrics(File file) {
        if (!file.getName().endsWith(".java")) {
            return null;
        }

        String packageName = "";
        String className = "";
        double tlocValue = 0.0;
        double tassertValue = 0.0;

        tloc locCounter = new tloc();
        tassert assertCounter = new tassert();

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                if (data.startsWith("package ")) {
                    packageName = data.substring(8, data.length() - 1).trim(); // extract package name without semicolon
                }

                if (data.startsWith("public class ")) {
                    className = data.substring(13).split(" ")[0].trim();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while processing the file: " + file.getPath());
            e.printStackTrace();
        }

        tlocValue = locCounter.computeTLOC(file.getAbsolutePath());
        tassertValue = assertCounter.computeAssertions(file.getAbsolutePath());

        tropcomp.ClassMetrics metrics = new tropcomp.ClassMetrics();
        metrics.path = file.getAbsolutePath();
        metrics.packageName = packageName;
        metrics.className = className;
        metrics.tloc = tlocValue;
        metrics.tassert = tassertValue;
        metrics.tcmp = (tassertValue == 0) ? 0 : tlocValue / tassertValue;

        return metrics;
    }
}
