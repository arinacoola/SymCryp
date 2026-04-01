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

    public static String[] topBigrams(int[][] bigrams, String alph, int top) {
        int m = alph.length();
        String[] res = new String[top];
        for (int i = 0; i < top; i++) {
            int max = -1;
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
            res[i] = "" + alph.charAt(b_i) + alph.charAt(b_j);
            System.out.println(res[i] + " - " + max);
            bigrams[b_i][b_j] = -1;
        }
        return res;
    }
}