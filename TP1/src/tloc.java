import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tloc {
    static boolean commentOpen = false;
    static boolean stringOpen = false;
    int countLine = 0;

    @Test
    void main(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();

                if(isValidLine(data))
                {
                    countLine ++;
                    //System.out.println(data);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(countLine);
    }
    private static boolean isValidLine(String line)
    {
        if (line.compareTo("") != 0 || line != null)
        {

            if(line.length() > 1)
            {
                /*
                int quoteOpen =  line.length() - line.replace("\"", "").length();
                int
                if(quoteOpen % 2 != 0 && !commentOpen)
                {
                    if(stringOpen)
                    {
                        stringOpen = false;
                    }
                    else
                    {
                        stringOpen = true;
                    }
                }*/
                String startWith = line.substring(0, 2);
                if(startWith.compareTo("/*") != 0 && startWith.compareTo("//") != 0)
                {
                    if(!commentOpen)
                    {
                        if(line.contains("/*"))
                        {
                            commentOpen = true;
                        }
                        return true;

                    }
                    if (commentOpen && line.contains("*/"))
                    {
                        if(line.length() == line.indexOf("*/") + 2)
                        {
                            commentOpen = false;
                        }
                        else {
                            return isValidLine(line.substring(line.indexOf("*/") + 2));
                        }
                    }
                }else {
                    if(startWith.compareTo("/*") == 0)
                    {
                        commentOpen = true;
                    }
                    return false;
                }
            }else if (line.length() == 1 && !commentOpen)
            {
                return true;
            }

        }

        return false;
    }


}
