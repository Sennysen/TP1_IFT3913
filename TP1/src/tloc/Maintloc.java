package tloc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

public class Maintloc {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        String path = args[0];
        tloc tlocCounter = new tloc();
        int lineCount = tlocCounter.computeTLOC(path);
        System.out.println("Total lines of code for file " + path + ": " + lineCount);
    }

}
