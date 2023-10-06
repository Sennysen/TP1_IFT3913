package tassert;

import java.io.IOException;

public class Maintassert {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("le nombre tassert est " + new tassert().computeAssert(args[0]));
    }



}
