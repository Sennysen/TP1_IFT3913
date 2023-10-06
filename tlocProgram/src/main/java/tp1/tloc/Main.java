package tp1.tloc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        String path = args[0];
        tloc tlocCounter = new tloc();
        int lineCount = tlocCounter.computeTLOC(path);
        System.out.println("Total lines of code for file " + path + ": " + lineCount);
    }

}
