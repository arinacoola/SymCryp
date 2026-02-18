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
}
