import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class tropcomp {

    public void writeTropComp(String dirPath, String outputPath, String seuil){
        List <scriptDetail> allScript = new tls().getTLS(dirPath,outputPath);
        for(scriptDetail a : allScript){
            if(a.tcmpValue < Double.parseDouble(seuil) && a.tlocValue < Double.parseDouble(seuil)){
                try (PrintStream outp = new PrintStream(new FileOutputStream(outputPath,true))) { // 'true' pour append
                    String fileDetail = a.pathName+ "    " + a.pkgName+ "    " + a.clsName + "    " + a.tlocValue + "      " + a.tassertValue + "      " + a.getTCMP();
                    outp.println(fileDetail);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
