public class Frequencies {
    public static int[] countLetterFrequencies(String cleanText) {
        int[] alphabet = new int[33];
        for (char c :cleanText.toCharArray()){
            int ind=0;
            if(c==' '){
                ind=32;
            }
            else{
                ind = c - 'а';
            }
            alphabet[ind]++;
        }
        return alphabet;
    }

    public static int[][] countBigramsIntersecting(String cleanText){
        int[][] bigrams = new int[33][33];
        for (int i = 0; i < cleanText.length() - 1; i++){
            int ind1=0;
            int ind2=0;
            char c1 = cleanText.charAt(i);
            char c2 = cleanText.charAt(i + 1);
            if(c1==' '){
                ind1 = 32;
            }
            else{
                ind1=c1-'а';
            }
            if(c2==' '){
                ind2=32;
            }
            else {
                ind2=c2-'а';
            }
            bigrams[ind1][ind2]++;
        }
        return bigrams;
    }

    public static int[][] countBigramsNoIntersecting(String cleanText){
        int[][] bigrams = new int[33][33];
        for (int i = 0; i < cleanText.length() - 1; i += 2){
            int ind1=0;
            int ind2=0;
            char c1 = cleanText.charAt(i);
            char c2 = cleanText.charAt(i + 1);
            if(c1==' '){
                ind1 = 32;
            }
            else{
                ind1=c1-'а';
            }
            if(c2==' '){
                ind2=32;
            }
            else {
                ind2=c2-'а';
            }
            bigrams[ind1][ind2]++;
        }
        return bigrams;
    }

    //без пробілу

    public static int[] countLetterFrequenciesNoSpace(String text) {
        int[] alphabet = new int[32];
        for (char c : text.toCharArray()) {
            alphabet[c - 'а']++;
        }
        return alphabet;
    }

    public static int[][] countBigramsIntersectingNoSpace(String text) {
        int[][] bigrams = new int[32][32];
        for (int i = 0; i < text.length() - 1; i++) {
            int a = text.charAt(i) - 'а';
            int b = text.charAt(i + 1) - 'а';
            bigrams[a][b]++;
        }
        return bigrams;
    }

    public static int[][] countBigramsNoIntersectingNoSpace(String text) {
        int[][] bigrams = new int[32][32];
        for (int i = 0; i < text.length() - 1; i += 2) {
            int a = text.charAt(i) - 'а';
            int b = text.charAt(i + 1) - 'а';
            bigrams[a][b]++;
        }
        return bigrams;
    }


}
