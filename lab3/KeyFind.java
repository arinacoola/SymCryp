import java.util.ArrayList;
import java.util.List;

public class KeyFind{
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
        int m = alph.length();
        int mod = m * m;
        int x1 = BigramNum.toNum(open1, alph);
        int x2 = BigramNum.toNum(open2, alph);
        int y1 = BigramNum.toNum(cipher1, alph);
        int y2 = BigramNum.toNum(cipher2, alph);
        long dx = NumberTheory.mod(x1 - x2, mod);
        long dy = NumberTheory.mod(y1 - y2, mod);
        List<Long> allA = NumberTheory.linearCongruence(dx, dy, mod);
        for (long a : allA) {
            long b = NumberTheory.mod(y1 - a * x1, mod);
            res.add(new Key(a, b));
        }
        return res;
    }
}