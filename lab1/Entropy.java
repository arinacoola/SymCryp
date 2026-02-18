public class Entropy {
    public static double calcH1(int[] counts, int n){
        double H1=0;
        for(int i=0;i<32;i++){
            if(counts[i]>0){
                double p = (double) counts[i] / n;
                H1-=p * (Math.log(p) / Math.log(2));
            }
        }
        return H1;
    }

    public static double calcH2Intersecting(int[][] bigramCounts, int n) {
        double tmp = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                if (bigramCounts[i][j] > 0) {
                    double p = (double) bigramCounts[i][j] / n;
                    tmp -= p * (Math.log(p) / Math.log(2));
                }
            }
        }
        double H2 = tmp / 2.0;
        return H2;
    }


}
