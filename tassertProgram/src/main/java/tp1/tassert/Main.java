package tp1.tassert;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("le nombre tassert est " + new tassert().computeAssert(args[0]));
    }

}
