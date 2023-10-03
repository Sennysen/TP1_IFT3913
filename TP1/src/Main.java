import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {


        testTropComp();// tester la class trop comp
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
            tropcomp.main(testArgs);
            System.out.println("tropcomp test completed. Check the output file for results.");
        } catch(Exception e) {
            System.out.println("Error encountered while testing tropcomp: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
