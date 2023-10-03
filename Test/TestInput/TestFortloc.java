public class TestFileForTLOC {

    public void exampleMethod() {
        int x = 1 + 1;  // This is a line of code

        // This is a single-line comment and should not count
        System.out.println("This is a line of code");

        String a = """
                /*\s
                        This is a multi-line comment
                        spanning multiple lines
                        and should not be counted\s
                        */
                
                
                """

        System.out.println("Another line of code");
    }
}
