package scorer;

import indexer.Postings;

import java.util.List;
import java.util.Map;

public class JelinikMercerSmoothing extends Scorer {

    private double lambda;
    private double C;
    public JelinikMercerSmoothing(double lambda,double C){
        this.lambda = lambda;
        this.C = C;
    }

    public double score(int tdf,int dl,int tcf){
        double dp = (1-lambda)*tdf/dl;
        double cp = lambda*tcf/C;
        return Math.log(dp+cp);
    }

    @Override
    public double score(List<String> Q, Postings l, Map<String, Integer> params) {
        return score(params.get("tdf"),params.get("dl"),params.get("tcf"));
    }
}
