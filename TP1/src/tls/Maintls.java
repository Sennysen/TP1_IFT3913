package tls;

import java.io.IOException;

public class Maintls {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        tls tlsCounter = new tls();
        if(args[1].equals("-o" ) ){
            // un output path est specifie
            String outputpath = args[1];
            String path = args[2];

            //tlsCounter.getTLS(path,outputpath,true);
        }
        else{

            String path = args[2];
            //tlsCounter.getTLS(path,null,false);
        }

    }
}
