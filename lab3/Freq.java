public class Freq {
    public static int[][] countBigramsAffine(String text, String alph) {
        int m = alph.length();
        int[][] bigrams = new int[m][m];
        for (int i = 0; i + 1 < text.length(); i += 2) {
            int a = alph.indexOf(text.charAt(i));
            int b = alph.indexOf(text.charAt(i + 1));
            if (a != -1 && b != -1) {
                bigrams[a][b]++;
            }
        }
        return bigrams;
    }
    public static void top5Bigrams(int[][] bigrams, String alph) {
        int m = alph.length();
        for (int i = 0; i < 5; i++) {
            int max = 0;
            int b_i = -1, b_j = -1;
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    if (bigrams[j][k] > max) {
                        max = bigrams[j][k];
                        b_i = j;
                        b_j = k;
                    }
                }
            }
            System.out.println("" + alph.charAt(b_i) + alph.charAt(b_j) + " - " + max
            );
            bigrams[b_i][b_j] = -1;
        }
    }
}