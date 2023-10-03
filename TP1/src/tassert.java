import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tassert {

    private int insCount = 0;
    private boolean commentOn = false;
    private boolean quoteOn = false;

    public int computeAssertions(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                processLine(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return insCount;
    }

    private void processLine(String line) {
        if(line == null || line.isEmpty()) {
            return;
        }

        if (line.contains("//")) {
            line = line.substring(0, line.indexOf("//"));
        }

        if (commentOn && line.contains("*/")) {
            line = line.substring(line.indexOf("*/") + 2);
            commentOn = false;
        }

        if (quoteOn && line.contains("\"")) {
            line = line.substring(line.indexOf("\"") + 1);
            quoteOn = false;
        }

        if (line.contains("/*")) {
            line = line.substring(0, line.indexOf("/*"));
            commentOn = true;
        }

        if (line.contains("\"")) {
            line = line.substring(0, line.indexOf("\""));
            quoteOn = true;
        }

        if (line.contains(";")) {
            for (String subLine : line.split(";")) {
                countAssertion(subLine);
            }
        } else {
            countAssertion(line);
        }
    }

    private void countAssertion(String line) {
        if (line.contains("assertArrayEquals(") || line.contains("assertEquals(")
                || line.contains("assertFalse(") || line.contains("assertNotEquals(")
                || line.contains("assertNotNull(") || line.contains("assertNotSame(")
                || line.contains("assertNull(") || line.contains("assertSame(")
                || line.contains("assertThat(") || line.contains("assertThrows(")
                || line.contains("assertTrue(") || line.contains("fail(")) {
            insCount++;
        }
    }
}
