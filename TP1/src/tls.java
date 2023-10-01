import org.junit.jupiter.api.Test;
import org.junit.runner.Result;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class tls {
    String packageName = "";
    String className = "";
    String title = "";



    @Test
    void main(File dir){
            //System.out.println(dir);
            boolean isTest = false;
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    main(new File(dir, children[i]));
                }
            }
            else {
                Path path = Paths.get(dir.toURI());

                try {
                    File myObj = new File(path.toUri());
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine().trim();
                        if(data.compareTo("import org.junit.jupiter.api.Test;") == 0){
                            isTest = true;
                            title = myObj.getName();
                        }

                        if(data.length() >= 8 && data.substring(0, 7).compareTo("package") == 0)
                        {
                            packageName = data.substring(8).trim().split(";")[0];
                            //System.out.println("package name: " + packageName);
                        }

                        if(data.length() >= 13 && data.substring(0, 12).compareTo("public class") == 0)
                        {
                            className = data.substring(12).split(" ")[1].trim();
                            //System.out.println("class name: " + className);
                        }



                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                if(isTest)
                {
                    tloc t1 = new tloc();
                    tassert t2 = new tassert();


                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    PrintStream out = System.out;
                    PrintStream printStream = new PrintStream(outputStream);
                    System.setOut(printStream);
                    t1.main(String.valueOf(path));
                    String tloc = outputStream.toString();
                    outputStream.reset();
                    t2.main(String.valueOf(path));
                    String tassert = outputStream.toString();

                    System.setOut(out);



                    System.out.print(path + "    " + packageName + "    " + className + "    " + tloc + "      " + tassert + "      " + Double.parseDouble(tloc)/Double.parseDouble(tassert));
                    System.out.println("\n");
                }
            }
        }
    }

