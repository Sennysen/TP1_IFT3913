import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class tassert {
    static int insCount = 0;
    static boolean commentOn = false;
    static boolean quoteOn = false;


    public int computeAssert(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                if(commentOn || quoteOn)
                {
                    if(data.contains("*/") || data.contains("\""))
                    {
                        //System.out.println("processe: " + data);
                        oneLine(data);
                    }
                }else {
                    //System.out.println("processe: " + data);
                    oneLine(data);
                    //System.out.println(insCount);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

       // System.out.print(insCount);
        return insCount;
    }
    public static void oneLine(String line)
    {   String left = "";
        String right = "";
        if(line.compareTo("") == 0 || line == null)
        {
            return;
        }

        if(line.contains("/*") || line.contains("\"") )
        {

            if(line.indexOf("/*") < line.indexOf("\"") && line.contains("\"") && line.contains("/*"))
            {
                left = line.substring(0, line.indexOf("/*"));
            }else  if(line.indexOf("/*") > line.indexOf("\"") && line.contains("\"") && line.contains("/*")) {
                left = line.substring(0, line.indexOf("\""));
            }else if(!line.contains("\"")){
                left = line.substring(0, line.indexOf("/*"));
            }else{
                left = line.substring(0, line.indexOf("\""));
            }

            right = line.substring(left.length());


            if(!right.contains("\"") && right.charAt(0) == '"' && quoteOn == false)
            {
                quoteOn = true;
                right = "";
            }else if(!right.contains("*/") && right.length() >= 2 && "/*".equals(right.substring(0, 2)))
            {
                commentOn = true;
                right = "";
            }else if(right.contains("\"") && right.charAt(0) == '"')
            {
                right = right.substring(right.indexOf("\"") + 1);
            }else if(right.contains("*/") && right.substring(0, 2).compareTo("/*") == 0)
            {
                right = right.substring(right.indexOf("*/") + 2);
            }


            oneLine(left);
            oneLine(right);
            return;
        }

        if(line.contains("//"))
        {
            left = line.substring(0, line.indexOf("//"));
            oneLine(left);
            return;
        }

        if(line.contains("*/"))
        {
            right = line.substring(line.indexOf("*/") + 2);
            oneLine(right);
            commentOn = false;
            return;
        }

        if(line.contains("\""))
        {
            right = line.substring(line.indexOf("\"") + 1);
            oneLine(right);
            quoteOn = false;
            return;
        }


        if(line.contains(";"))
        {
            left = line.substring(0, line.indexOf(";"));
            right = line.substring(line.indexOf(";") + 1);
            oneLine(left);
            oneLine(right);
            return;
        }

        //if all special chars are not presents, now one "line" could contain at most one assertion

        if(line.contains("assertArrayEquals(") || line.contains("assertEquals(") || line.contains("assertFalse(") || line.contains("assertNotEquals(") ||
                line.contains("assertNotNull(") || line.contains("assertNotSame(") || line.contains("assertNull(") || line.contains("assertSame(") ||
                line.contains("assertThat(") || line.contains("assertThrows(") || line.contains("assertTrue(") || line.contains("fail("))
        {
            insCount++;
        }
        return;
    }


}