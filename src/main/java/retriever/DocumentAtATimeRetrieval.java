package retriever;

import indexer.Postings;
import indexer.PostingsList;
import indexer.SimpleIndex;
import javafx.geometry.Pos;
import scorer.Scorer;

import java.io.IOException;
import java.util.*;


public class DocumentAtATimeRetrieval extends Retriever{
    public Map<String,Integer> toQfMap(List<String> Q){
        Map<String,Integer> map = new HashMap<>();
        for(String term: Q){
            map.put(term,map.getOrDefault(term,0)+1);
        }
        return map;
    }
    public List<Entry> retrieval(SimpleIndex index, List<String> Q, int I, Scorer G, Scorer F, int k) throws IOException {
        List<PostingsList> L = new ArrayList<PostingsList>();
        ArrayList<Map<Integer, Integer>> result = new ArrayList<Map<Integer, Integer>>();
        PriorityQueue<Entry> R = new PriorityQueue();
        Map<String,Integer> qf = toQfMap(Q);
        for(String term: Q){
            PostingsList l = index.get(term);
            l.setTerm(term);
            L.add(l);
        }
        for(int docID = 1; docID<=I;docID++){
            double sd = 0;
            for(PostingsList l:L){
                if(l.getCurr_pos()==null) continue;
                if(l.getCurr_pos().getDocID()==docID){
                    Map<String,Integer> params = new HashMap<>();
                    params.put("df",index.getDocFreq(l.getTerm()));
                    params.put("tcf",index.getTermFreq(l.getTerm()));
                    params.put("tdf",l.getCurr_pos().getTermFreq());
                    params.put("dl",index.getDocLen(docID));
                    params.put("qf",qf.getOrDefault(l.getTerm(),0));
                    sd += G.score(Q,l.getCurr_pos(),params)*F.score(Q,l.getCurr_pos(),params);
                }
                l.movePastDocument(docID);
            }
            R.add(new Entry(docID,sd));
            if(R.size()>k){
                R.poll();
            }
        }
        List<Entry> output = new ArrayList<Entry>();
        while (!R.isEmpty()){
            output.add(R.poll());
        }
        Collections.reverse(output);
        return output;
    }
}
