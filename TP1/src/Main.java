import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

       
        //testTLOCMethod();  // tester la class tloc
        //testTLSMethod();
        //testTropComp();
    }
    public static void testTLSMethod(){
        String[] testArgs = {
                "-o",
                "Test/TestOutput/output.csv", // output
                "Test/TestInput/Title",

        };
        String outputpath = testArgs[1];
        String path = testArgs[2];
        tls tlsCounter = new tls();
        tlsCounter.getTLS(path,outputpath);
    }

    public static void testTLOCMethod() {
        String[] testArgs = {

                "Test/TestInput/TestFortloc.java"

        };
        
        if (testArgs.length != 1) {
            System.out.println("Usage: tloc <file_path>");
            return;
        }

        String path = testArgs[0];
        tloc tlocCounter = new tloc();
        int lineCount = tlocCounter.computeTLOC(path);
        System.out.println("Total lines of code for file " + path + ": " + lineCount);
    }
    public static void testTropComp() {
        // Simulate command line
        // java main -o /Users/yuningsun/Documents/TestOutput/output.csv Test/TestInput 1
        String[] testArgs = {
                "-o",
                "Test/TestOutput/output.csv", // output
                "Test/TestInput", //
                "1" // Thresh hold
        };

        try {
            tropcomp a = new tropcomp();
            a.writeTropComp(testArgs[2],testArgs[1],testArgs[3]);
            System.out.println("tropcomp test completed. Check the output file for results.");
        } catch (Exception e) {
            System.out.println("Error encountered while testing tropcomp: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
