public class Main {
    public static void main(String[] args) {

        tloc t = new tloc();
        t.main("CategoryLineAnnotationTest.java");

        tassert t2 = new tassert();
        t2.main("CategoryLineAnnotationTest.java");
    }
}