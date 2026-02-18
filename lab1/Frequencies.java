public class Frequencies {
    public static int[] countLetterFrequencies(String cleanText) {
        int[] alphabet = new int[32];
        for (char c :cleanText.toCharArray()){
            int ind=0;
            if(c==' '){
                ind=31;
            }
            else{
                ind = c - 'а';
            }
            alphabet[ind]++;
        }
        return alphabet;
    }

    public static int[][] countBigrams(String cleanText){
        int[][] bigrams = new int[32][32];
        for (int i = 0; i < cleanText.length() - 1; i++){
            int ind1=0;
            int ind2=0;
            char c1 = cleanText.charAt(i);
            char c2 = cleanText.charAt(i + 1);
            if(c1==' '){
                ind1 = 31;
            }
            else{
                ind1=c1-'а';
            }
            if(c2==' '){
                ind2=31;
            }
            else {
                ind2=c2-'а';
            }
            bigrams[ind1][ind2]++;
        }
        return bigrams;
    }

}
