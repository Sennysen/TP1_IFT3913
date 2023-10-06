package tropcomp;

import java.util.PriorityQueue;

public class tropcomp {

    public void writeTropComp(String dirPath, String outputPath, String seuil, Boolean outP ){
        tls a = new tls();
        int totalNumber =  a.getPercentileNbFile(dirPath);
        int topN = (int) Math.ceil((Double.parseDouble(seuil)/100) * totalNumber);
        System.out.println("Le nombre de top est " + topN);

            //PriorityQueue<scriptDetail> queue = a.getTLS(dirPath,topN);

//            Set<scriptDetail> setTLOC = new HashSet<>(a.getTLS(dirPath,topN,"TLOC"));
//
//            Set<scriptDetail> setTCMP = new HashSet<>(a.getTLS(dirPath,topN,"TCMP"));
//
//            Set<scriptDetail> setD = new HashSet<>();

        PriorityQueue<scriptDetail> queueTLOC = a.getTLS(dirPath,topN,"TLOC");
        PriorityQueue<scriptDetail> queueTCMP = a.getTLS(dirPath,topN,"TCMP");

        //System.out.println("the last file is " + queueTLOC.poll().toString());
        Integer tlocVinPercentile = queueTLOC.poll().getTLOC();
        double tcmpVinPercentile = queueTCMP.poll().getTCMP();


            System.out.println("Fichier trop complique tloc threshhold min value est " + tlocVinPercentile + " tcmp threshhold value est " + tcmpVinPercentile);
            if(outP){
                a.getTLS(dirPath, outputPath,true,tlocVinPercentile,tcmpVinPercentile);
            }else{
                a.getTLS(dirPath, null,false,tlocVinPercentile,tcmpVinPercentile);
            }
            a.getTLS(dirPath, null,false,tlocVinPercentile,tcmpVinPercentile);



        }

    }


