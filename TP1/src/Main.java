import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

       String testCase = args[0];
       switch(testCase){
           case "tloc":
               callTLOCMethod(args);
               break;
           case "tassert":
               callTASSERTMethod(args);
               break;
           case "tls":
               callTLSMethod(args);
               break;
           case "tropcomp":
               callTropComp(args);
               break;
           default:
               System.out.println("Vous n'aviez pas rentrer un nom de fonction valide, " +
                       "veuillez choisir tloc , tassert , tls ou tropcomp");
               // Test/jfreechart-master

       };
    }
    public static void callTASSERTMethod(String[] args){
        String[] testArgs = {
                "Test/TestInput/Title"
        };
        tassert tA = new tassert();
        tA.computeAssert(args[1]);
    }

    public static void callTLSMethod(String[] args){
        String[] testArgs = {
                "-o",
                "Test/TestOutput/output.csv", // output
                "Test/TestInput/Title",

        };
        tls tlsCounter = new tls();
        if(args[1].equals("-o" ) ){
            // un output path est specifie
            String outputpath = args[1];
            String path = args[2];

            tlsCounter.getTLS(path,outputpath,true);
        }
        else{

            String path = args[2];
            tlsCounter.getTLS(path,null,false);
        }
    }

    public static void callTLOCMethod(String[] args) {
        String[] testArgs = {
                "Test/TestInput/TestFortloc.java"
        };

        String path = args[1];
        tloc tlocCounter = new tloc();
        int lineCount = tlocCounter.computeTLOC(path);
        System.out.println("Total lines of code for file " + path + ": " + lineCount);
    }
    public static void callTropComp(String[] args) {
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

            if(args[1].equals("-o" )){

                String outputPath = args[2];
                String inputPath = args[3];
                String seuil = args[4];
                a.writeTropComp(inputPath,outputPath,seuil, true);

            }else{

                String inputPath = args[1];
                String seuil = args[2];
                a.writeTropComp(inputPath,null,seuil,false);

            }

            System.out.println("tropcomp test completed. Check the output file for results.");
        } catch (Exception e) {
            System.out.println("Error encountered while testing tropcomp: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
