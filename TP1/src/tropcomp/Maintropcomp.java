package tropcomp;

import java.io.IOException;

public class Maintropcomp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

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
                int fileNb = new tls().getPercentileNbFile(inputPath);
                //System.out.println(a);
                a.writeTropComp(inputPath,outputPath,seuil,true);


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
