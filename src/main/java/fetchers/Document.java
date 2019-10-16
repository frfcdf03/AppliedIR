package fetchers;

import java.util.Map;

public abstract class Document {
    private int docID;
    public Document(int docID){
        this.docID = docID;
    }
    public abstract String getDocument();
    public abstract Map<String,String> getMetaInfo();
    public int getDocID(){
        return this.docID;
    }
}
