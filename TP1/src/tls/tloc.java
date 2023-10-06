package tls;

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
        boolean stringOpen = false;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().replaceAll("\\s","");
                // est une ligne de code avec debut et hors commentaire
                if(data.contains("=\"") && !data.contains("\";") && !commentOpen ){
                    stringOpen = true;
                }else if (data.contains("\";") && !commentOpen){
                    stringOpen = false;
                }
                // Update the commentOpen status
                if (data.contains("/*") && !data.contains("*/")) {
                    commentOpen = true;
                }

                if (isValidLine(data,commentOpen,stringOpen)) {
                    countLine++;

                    //System.out.println(data);
                    //System.out.println("commentOpen"+": " + commentOpen);
                    //System.out.println("stringOpen"+": " + stringOpen);
                    //System.out.println("line number now is"+": " + countLine);
                }
                if (data.contains("*/")) {
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

    private boolean isValidLine(String line, boolean commentOpen, boolean stringOpen) {
        if (line == null ) {
            return false;
        }
        if(line.isEmpty() && stringOpen){
            return true;
        }
        if(line.isEmpty() && !stringOpen){
            return false;
        }

        if (line.startsWith("//")) { // une ligne de comment
            return false;
        }

        if (stringOpen) {
            return true; // est dans une string donc une ligne valide
        }

        if (!commentOpen ) {
            return true;
        }

        return false;
    }
}
