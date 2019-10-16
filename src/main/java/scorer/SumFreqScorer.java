package scorer;

import indexer.Postings;

import java.util.List;
import java.util.Map;

public class SumFreqScorer extends Scorer {


    public SumFreqScorer(){

    }
    public double score(List<String> Q, Postings l, Map<String, Integer> params) {
        return l.getTermFreq();
    }
}
