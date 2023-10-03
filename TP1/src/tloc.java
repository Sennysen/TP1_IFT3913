import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tloc {

    /**
     * Computes the Total Lines of Code (TLOC) for the given file path.
     *
     * @param path The file path.
     * @return The TLOC.
     */
    public int computeTLOC(String path) {
        int countLine = 0;
        boolean commentOpen = false;

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                if (isValidLine(data, commentOpen)) {
                    countLine++;
                }

                // Update the commentOpen status
                if (data.contains("/*") && !data.contains("*/")) {
                    commentOpen = true;
                } else if (data.contains("*/")) {
                    commentOpen = false;
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return countLine;
    }

    private boolean isValidLine(String line, boolean commentOpen) {
        if (line == null || line.isEmpty()) {
            return false;
        }

        if (line.startsWith("//")) { // Single line comment
            return false;
        }

        if (line.contains("/*") && !line.contains("*/")) {
            return true; // Count the starting line of block comments
        }

        if (!commentOpen) {
            return true;
        }

        return false; // If it's a continued comment line, it won't be valid
    }
}
