import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

       // tloc t = new tloc();
       // t.main("CategoryLineAnnotationTest.java");

       // tassert t2 = new tassert();
        //t2.main("CategoryLineAnnotationTest.java");

        tls t3 = new tls();
        t3.main(new File("annotations"));
    }
}