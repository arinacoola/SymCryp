import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyFind {
    public static class Key {
        long a;
        long b;

        Key(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "(a=" + a + ", b=" + b + ")";
        }
    }

    public static List<Key> findKeys(String open1, String open2, String cipher1, String cipher2, String alph) {
        List<Key> res = new ArrayList<>();
        Set<String> used = new HashSet<>();
        int m = alph.length();
        int mod = m * m;
        int x1 = BigramNum.toNum(open1, alph);
        int x2 = BigramNum.toNum(open2, alph);
        String[][] variants = {
                {cipher1, cipher2},
                {cipher2, cipher1}
        };
        for (String[] var : variants) {
            int y1 = BigramNum.toNum(var[0], alph);
            int y2 = BigramNum.toNum(var[1], alph);
            long dx = NumberTheory.mod(x1 - x2, mod);
            long dy = NumberTheory.mod(y1 - y2, mod);
            List<Long> allA = NumberTheory.linearCongruence(dx, dy, mod);
            for (long a : allA) {
                if (NumberTheory.gcd(a, mod) != 1) {
                    continue;
                }
                long b = NumberTheory.mod(y1 - a * x1, mod);
                String mark = a + ":" + b;
                if (used.contains(mark)) {
                    continue;
                }
                used.add(mark);
                res.add(new Key(a, b));
            }
        }
        return res;
    }
}