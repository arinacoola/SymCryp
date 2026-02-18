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
}
