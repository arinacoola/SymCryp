public class AffineDecrypt {
    public static String decrypt(String cipherText, long a, long b, String alph) {
        int m = alph.length();
        int mod = m * m;
        Long invA = NumberTheory.modInverse(a, mod);
        if (invA == null) {
            return "";
        }
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i + 1 < cipherText.length(); i += 2) {
            String bg = cipherText.substring(i, i + 2);
            int y = BigramNum.toNum(bg, alph);
            long x = NumberTheory.mod(invA * NumberTheory.mod(y - b, mod), mod);
            plain.append(BigramNum.fromNum((int) x, alph));
        }
        return plain.toString();
    }
}
