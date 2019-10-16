package app;

import indexer.SimpleIndex;

public class IndexBuilder {
    public static void main(String[] args){
        int compress_flag =0;
        if(args.length>0){
            compress_flag =Integer.parseInt(args[0]);
        }
        try {
            String filename= "shakespeare-scenes.json";
            SimpleIndex index = new SimpleIndex(filename);
            index.build();
            System.out.println("Compressed:"+compress_flag);
            System.out.println("Volcab Size:"+index.getVolcabury().size());
            System.out.println("Total Terms:"+index.getTotalTermsCount());
            System.out.println("Total Docs:"+index.getDocNum());
            System.out.println("Max Doc:"+index.docToScene(index.getMaxDocLen()[0])+" Len:"+index.getMaxDocLen()[1]);
            System.out.println("Min Doc:"+index.docToScene(index.getMinDocLen()[0])+" Len:"+index.getMinDocLen()[1]);
            System.out.println("Ave Doc len:"+index.getAveDocLen());
            System.out.println("Max Play:"+index.getMaxPlaylen().getKey()+" Len:"+index.getMaxPlaylen().getValue());
            System.out.println("Min Play:"+index.getMinPlaylen().getKey()+" Len:"+index.getMinPlaylen().getValue());


            index.to_disk(compress_flag);



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
