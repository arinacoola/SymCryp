public class BigramNum {
    public static int toNum(String bg, String alph) {
        if (bg.length() != 2) {
            throw new IllegalArgumentException("bigram must have length 2");
        }
        int f = alph.indexOf(bg.charAt(0));
        int s = alph.indexOf(bg.charAt(1));
        if (f == -1 || s == -1) {
            throw new IllegalArgumentException("wrong bigram: " + bg);
        }
        return f * alph.length() + s;
    }

    public static String fromNum(int x, String alph) {
        int m = alph.length();
        if (x < 0 || x >= m * m) {
            throw new IllegalArgumentException("number out of range: " + x);
        }
        int f = x / m;
        int s = x % m;
        return "" + alph.charAt(f) + alph.charAt(s);
    }
}