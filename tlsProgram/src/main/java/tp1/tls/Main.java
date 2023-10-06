package tp1.tls;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        tls tlsCounter = new tls();
        if(args[0].equals("-o" ) ){
            // un output path est specifie
            String outputpath = args[1];
            String path = args[2];

            tlsCounter.getTLS(path,outputpath,true);
        }
        else{

            String path = args[0];
            tlsCounter.getTLS(path,null,false);
        }

    }
}
