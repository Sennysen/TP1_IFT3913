public class TestFileForTLOC {

    public void exampleMethod() {
        int x = 1 + 1;  // This is a line of code

        // This is a single-line comment and should not count
        System.out.println("This is a line of code");

        String a = """
                /*
                        This is a multi-line comment
                        spanning multiple lines
                        and should not be counted\s
                        */
                
                
                """;
        /**
         * Computes the Total Lines of Code (TLOC) for the given file path.
         *
         * @param path The file path.
         * @return The TLOC.
         */
        System.out.println("Another line of code");
    }
}