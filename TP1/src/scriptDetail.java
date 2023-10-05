import java.nio.file.Path;
import java.text.DecimalFormat;

public class scriptDetail {
    String pkgName;
    String clsName;
    String pathName;
    Integer tlocValue;
    Integer tassertValue;
    Double tcmpValue;
    private static final DecimalFormat rounding2 = new DecimalFormat("0.00");

    scriptDetail(String pkg,String cls, String path, Integer tlocV, Integer tassertV){
            this.pkgName = pkg;
            this.clsName = cls;
            this.pathName = path;
            this.tlocValue = tlocV;
            this.tassertValue =tassertV;
            this.tcmpValue = Double.parseDouble(rounding2.format(tlocV/tassertV));
    }

    public double getTCMP(){
        return this.tcmpValue;
    }

}
