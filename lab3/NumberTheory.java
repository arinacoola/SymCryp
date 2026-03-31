import java.util.ArrayList;
import java.util.List;

public class NumberTheory{
    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static class EgcdRes {
        long gcd;
        long x;
        long y;
        EgcdRes(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    public static EgcdRes extendedGcd(long a, long b) {
        long oldR = a, r = b;
        long oldX = 1, x = 0;
        long oldY = 0, y = 1;
        while (r != 0) {
            long q = oldR / r;
            long tempR = oldR - q * r;
            oldR = r;
            r = tempR;
            long tempX = oldX - q * x;
            oldX = x;
            x = tempX;
            long tempY = oldY - q * y;
            oldY = y;
            y = tempY;
        }
        return new EgcdRes(oldR, oldX, oldY);
    }

    public static long mod(long value, long mod) {
        long res = value % mod;
        if (res < 0) {
            res += mod;
        }
        return res;
    }
    public static Long modInverse(long a, long mod) {
        a = mod(a, mod);
        EgcdRes res = extendedGcd(a, mod);
        if (res.gcd != 1) {
            return null;
        }
        return mod(res.x, mod);
    }

    public static List<Long> linearCongruence(long a, long b, long n) {
        List<Long> solv = new ArrayList<>();
        a = mod(a, n);
        b = mod(b, n);
        long d = gcd(a, n);
        if (b % d != 0) {
            return solv;
        }
        if (d == 1) {
            Long inv = modInverse(a, n);
            if (inv != null) {
                solv.add(mod(inv * b, n));
            }
            return solv;
        }
        long a1 = a / d;
        long b1 = b / d;
        long n1 = n / d;
        Long inv = modInverse(a1, n1);
        if (inv == null) {
            return solv;
        }
        long x0 = mod(inv * b1, n1);
        for (long k = 0; k < d; k++) {
            solv.add(x0 + k * n1);
        }
        return solv;
    }
}
