import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Vigenere {
    private static final String ALPHABET = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final int M = ALPHABET.length();
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("lab2/Chehov_Anton__Tolstyi_i_tonkii_www.Litmir.net_72388.txt"));
        text = norm(text);
        System.out.printf("open text ind of coincidence = %.6f%n", indOfCoincidence(text));
        System.out.println();
        String[] keys = {
                "он",
                "лес",
                "вода",
                "весна",
                "криптоанализ"
        };
        for (String key : keys) {
            String encrypted = encrypt(text, key);
            double ioc = indOfCoincidence(encrypted);
            System.out.println("key: " + key + " (r=" + key.length() + ")");
            System.out.printf("ind of coincidence = %.6f%n", ioc);
            System.out.println(encrypted.substring(0, Math.min(150, encrypted.length())));
            System.out.println();
        }

        String var6 = Files.readString(Path.of("lab2/var6.txt"));
        var6 = norm(var6);
        System.out.println("variant 6 period check:");
        System.out.println();
        for (int r = 2; r <= 30; r++) {
            double avg = averageIndPeriod(var6, r);
            System.out.printf("r = %2d  ind of coincidence = %.6f%n", r, avg);
        }
        String testKey = "весна";
        String testCipher = encrypt(text, testKey);
        System.out.println("check period for key: " + testKey);
        System.out.println();
        for (int r = 2; r <= 30; r++) {
            double avg = averageIndPeriod(testCipher, r);
            System.out.printf("r = %2d  ind of coincidence = %.6f%n", r, avg);
        }
        int keyLen = 17;
        String[] blocks = splitBlocks(var6,keyLen);
        System.out.println();
        System.out.println("blocks for r = " + keyLen + ":");
        System.out.println();
        for (int i = 0; i < blocks.length; i++) {
            System.out.println("block " + i + ":");
            System.out.println(blocks[i]);
            System.out.println();
        }
        System.out.println("most frequent chars in blocks: ");
        for (int i = 0; i < blocks.length; i++) {
            char c = mostFrequentChar(blocks[i]);
            System.out.println("block " + i + ": " + c);
        }
        StringBuilder roughKey = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            char blockCh = mostFrequentChar(blocks[i]);
            char keyCh = getKeyChar(blockCh, 'о');
            roughKey.append(keyCh);
        }
        System.out.println();
        System.out.println("rough key: " + roughKey);
    }

    private static String norm(String text) {
        text = text.toLowerCase().replace('ё', 'е');
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int x = ALPHABET.indexOf(text.charAt(i));
            int k = ALPHABET.indexOf(key.charAt(i % key.length()));
            int y = (x + k) % M;
            result.append(ALPHABET.charAt(y));
        }
        return result.toString();
    }

    private static double indOfCoincidence(String text) {
        int n = text.length();
        if (n < 2) {
            return 0.0;
        }
        int[] counts = new int[M];
        for (int i = 0; i < text.length(); i++) {
            int idx = ALPHABET.indexOf(text.charAt(i));
            counts[idx]++;
        }
        long sum = 0;
        for (int count : counts) {
            sum += (long) count * (count - 1);
        }
        return (double) sum / (n * (n - 1));
    }

    private static String[] splitBlocks(String text, int r) {
        StringBuilder[] blocks = new StringBuilder[r];
        for (int i = 0; i < r; i++) {
            blocks[i] = new StringBuilder();
        }
        for (int i = 0; i < text.length(); i++) {
            blocks[i % r].append(text.charAt(i));
        }
        String[] result = new String[r];
        for (int i = 0; i < r; i++) {
            result[i] = blocks[i].toString();
        }
        return result;
    }

    private static double averageIndPeriod(String text, int r) {
        String[] blocks = splitBlocks(text, r);
        double sum = 0.0;
        for (String block : blocks) {
            sum += indOfCoincidence(block);
        }
        return sum / r;
    }

    private static char mostFrequentChar(String text) {
        int[] counts = new int[M];
        for (int i = 0; i < text.length(); i++) {
            int idx = ALPHABET.indexOf(text.charAt(i));
            counts[idx]++;
        }
        int bestInd = 0;
        for (int i = 1; i < M; i++) {
            if (counts[i] > counts[bestInd]) {
                bestInd = i;
            }
        }
        return ALPHABET.charAt(bestInd);
    }

    private static char getKeyChar(char blockCh, char assumedCh) {
        int y = ALPHABET.indexOf(blockCh);
        int x = ALPHABET.indexOf(assumedCh);
        int k = (y - x + M) % M;
        return ALPHABET.charAt(k);
    }

    private static String decrypt(String text, String key) {
        StringBuilder rslt = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int y = ALPHABET.indexOf(text.charAt(i));
            int k = ALPHABET.indexOf(key.charAt(i % key.length()));
            int x = (y - k + M) % M;
            rslt.append(ALPHABET.charAt(x));
        }
        return rslt.toString();
    }
}