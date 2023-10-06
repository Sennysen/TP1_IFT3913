import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class tls {
    String packageName = "";
    String className = "";
    String title = "";
    List<scriptDetail> scriptDetailList = new ArrayList<>();
    @Test
    public List<scriptDetail> getTLS(String dirPath, String outputPath, Boolean Opath) {
        boolean isTest = false;

        File dir = new File(dirPath);  // Convert the directory path string to a File object

        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {  // Ensure children is not null before accessing its length
                for (int i = 0; i < children.length; i++) {
                    String childPath = new File(dir, children[i]).getAbsolutePath();
                    List<scriptDetail> childDetails = getTLS(childPath,outputPath, Opath);  // Recursive call with the child's absolute path
                    scriptDetailList.addAll(childDetails);
                }
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
                        System.out.println("class name: " + className);
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
                Integer tlocV = t1.computeTLOC(String.valueOf(path));
                String tloc = outputStream.toString();
                outputStream.reset();
                Integer tassertV = t2.computeAssert(String.valueOf(path));
                String tassert = outputStream.toString();

                System.setOut(out);

                Path currentDirectory = Paths.get("").toAbsolutePath();  // Path Courrant
                Path absolutePath =path;

                Path relativePath = currentDirectory.relativize(absolutePath);

                String fPath = "./" + relativePath.toString();

                scriptDetail fileAlpha = new scriptDetail(packageName,className,fPath,tlocV,tassertV);
                scriptDetailList.add(fileAlpha);



                String fileDetail = fPath+ "    " + packageName + "    " + className + "    " + tloc + "      " + tassert + "      " + fileAlpha.getTCMP();
                System.out.println("\n");
                System.out.print(fPath + "    " + packageName + "    " + className + "    " + tloc + "      " + tassert + "      " + fileAlpha.getTCMP());
                System.out.println("\n");
                if(Opath) {
                    try (PrintStream outp = new PrintStream(new FileOutputStream(outputPath, true))) { // 'true' pour append
                        outp.println(fileDetail);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return scriptDetailList;
    }
}
