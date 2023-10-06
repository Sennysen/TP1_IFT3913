package tropcomp;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class tls {
    String packageName = "";
    String className = "";
    String title = "";
    tloc t1 = new tloc();

    PriorityQueue<scriptDetail> queueTLOC = new PriorityQueue<>(Comparator.comparingInt(scriptDetail::getTLOC));
    PriorityQueue<scriptDetail> queueTCMP = new PriorityQueue<>(Comparator.comparingDouble(scriptDetail::getTCMP));

    List<scriptDetail> scriptDetailList = new ArrayList<>();

    public void getTLS(String dirPath, String outputPath, Boolean Opath) {
        boolean isTest = false;

        File dir = new File(dirPath);  // Convert the directory path string to a File object

        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {  // Ensure children is not null before accessing its length
                for (int i = 0; i < children.length; i++) {
                    String childPath = new File(dir, children[i]).getAbsolutePath();
                    getTLS(childPath,outputPath, Opath);  // Recursive call with the child's absolute path
                    //scriptDetailList.addAll(childDetails);
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
                //scriptDetailList.add(fileAlpha);


                //String fileDetail = fPath+ "    " + packageName + "    " + className + "    " + tloc + "      " + tassert + "      " + fileAlpha.getTCMP();
                //System.out.println("\n");
                //System.out.print(fileAlpha.toString());
                //System.out.println("\n");

                    if(Opath) {
                        try (PrintStream outp = new PrintStream(new FileOutputStream(outputPath, true))) { // 'true' pour append
                            outp.println(fileAlpha.toString());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println(fileAlpha.toString());
                        System.out.println();
                    }


            }
        }

    }

    public void getTLS(String dirPath, String outputPath, Boolean Opath, Integer tlocThreshholdMin, Double tcmpThreshholdMin) {
        boolean isTest = false;

        File dir = new File(dirPath);  // Convert the directory path string to a File object

        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {  // Ensure children is not null before accessing its length
                for (int i = 0; i < children.length; i++) {
                    String childPath = new File(dir, children[i]).getAbsolutePath();
                    getTLS(childPath,outputPath, Opath, tlocThreshholdMin, tcmpThreshholdMin);  // Recursive call with the child's absolute path
                    //scriptDetailList.addAll(childDetails);
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
                //scriptDetailList.add(fileAlpha);


                //String fileDetail = fPath+ "    " + packageName + "    " + className + "    " + tloc + "      " + tassert + "      " + fileAlpha.getTCMP();
                //System.out.println("\n");
                //System.out.print(fileAlpha.toString());
                //System.out.println("\n");
                if(tlocV >= tlocThreshholdMin && fileAlpha.getTCMP() >= tcmpThreshholdMin){
                    if(Opath) {
                        try (PrintStream outp = new PrintStream(new FileOutputStream(outputPath, true))) { // 'true' pour append
                            outp.println(fileAlpha.toString());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("got one "+fileAlpha.toString());
                    }
                }

            }
        }

    }
    @Test
    public PriorityQueue<scriptDetail> getTLS(String dirPath, Integer top, String whichParam) {
        boolean isTest = false;

        File dir = new File(dirPath);  // Convert the directory path string to a File object

        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {  // Ensure children is not null before accessing its length
                for (int i = 0; i < children.length; i++) {
                    String childPath = new File(dir, children[i]).getAbsolutePath();
                    //getTLS(new File(dir, children[i]));
                    getTLS(childPath, top,whichParam);  // Recursive call with the child's absolute path


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



                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream out = System.out;
                PrintStream printStream = new PrintStream(outputStream);
                System.setOut(printStream);
                Integer tlocV = t1.computeTLOC(String.valueOf(path));
                //String tloc = outputStream.toString();
                //outputStream.reset();
                int tassertV = new tassert().computeAssert(String.valueOf(path));
                //String tassert = outputStream.toString();

                System.setOut(out);
                //System.out.println("The path is " + String.valueOf(path) + " the tassertV is " + new tassert().computeAssert(String.valueOf(path)) );

                Path currentDirectory = Paths.get("").toAbsolutePath();  // Path Courrant
                Path absolutePath =path;

                Path relativePath = currentDirectory.relativize(absolutePath);

                String fPath = "./" + relativePath.toString();

                scriptDetail fileAlpha = new scriptDetail(packageName,className,fPath,new tloc().computeTLOC(String.valueOf(path)),new tassert().computeAssert(String.valueOf(path)));

                //System.out.println("le fichier est "+fileAlpha.toString());

                //scriptDetailList.add(fileAlpha);
                if(whichParam.equals("TLOC")){
                    queueTLOC.add(fileAlpha);
                    if (queueTLOC.size() > top) { // enleve le plus petit
                        queueTLOC.poll();
                    }
                    //System.out.println("Le fichier qu'on a un fichier "+fileAlpha.toString());
                }else{
                    queueTCMP.add(fileAlpha);
                    if (queueTCMP.size() > top) { // enleve le plus petit
                        queueTCMP.poll();
                    }
                   // System.out.println("Le fichier qu'on a un fichier "+fileAlpha.toString());
                }

                // After processing all files:
                //List<scriptDetail> topDetails = new ArrayList<>(queue);
                //Collections.reverse(topDetails); // reverse to get the list in descending order of tlocV

            }

        }
        if(whichParam.equals("TLOC")){
            return queueTLOC;
        }else{
            return queueTCMP;
        }

    }

    @Test
    public int getPercentileNbFile(String dirPath) { // get number of file
        int number = 0;
        boolean isTest = false;

        File dir = new File(dirPath);

        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    String childPath = new File(dir, children[i]).getAbsolutePath();
                    number += getPercentileNbFile(childPath);  // Update the number based on the recursive call
                }
            }
        } else {
            Path path = Paths.get(dir.toURI());
            try {
                File myObj = new File(path.toUri());
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().trim();
                    if(data.equals("import org.junit.jupiter.api.Test;")) {
                        isTest = true;
                    }
                    if(data.startsWith("package")) {
                        packageName = data.substring(8).trim().split(";")[0];
                    }
                    if(data.startsWith("public class")) {
                        className = data.substring(12).split(" ")[1].trim();
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            if(isTest) {
                number++;
            }
        }

        return number;
    }



}
