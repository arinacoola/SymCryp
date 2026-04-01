import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main3 {
    public static void main(String[] args) throws Exception {
        String alph1 = "абвгдежзийклмнопрстуфхцчшщьыэюя";//для варіанту 6 альфавіт 1 + a=441, b=310
        String alph2 = "абвгдежзийклмнопрстуфхцчшщыьэюя";
        String[] alphabets = {alph1, alph2};
        String cipherText = Files.readString(Path.of("lab3/06.txt"));
        cipherText = cipherText.toLowerCase().replace('ё', 'е').replace('ъ', 'ь').replaceAll("[^а-я]", "");
        String[] lang = {"ст", "но", "то", "на", "ен"};
        for (int s = 0; s < alphabets.length; s++) {
            String alph = alphabets[s];
            System.out.println("ALPHABET " + (s + 1));
            int[][] bigrams = Freq.countBigramsAffine(cipherText, alph);
            System.out.println("TOP BIGRAMS:");
            String[] cipher = Freq.topBigrams(bigrams, alph, 5);
            System.out.println();
            Set<String> used = new HashSet<>();
            for (int i = 0; i < lang.length; i++) {
                for (int j = i + 1; j < lang.length; j++) {
                    for (int k = 0; k < cipher.length; k++) {
                        for (int t = k + 1; t < cipher.length; t++) {
                            List<KeyFind.Key> keys =
                                    KeyFind.findKeys(lang[i], lang[j], cipher[k], cipher[t], alph);
                            for (KeyFind.Key key : keys) {
                                String mark = key.a + ":" + key.b;
                                if (used.contains(mark)) {
                                    continue;
                                }
                                used.add(mark);
                                String plain = AffineDecrypt.decrypt(cipherText, key.a, key.b, alph);
                                if (RussianCheck.isMeaningful(plain)) {
                                    System.out.println("from: " + lang[i] + ", " + lang[j] + " => " + cipher[k] + ", " + cipher[t]);
                                    System.out.println("key = " + key);
                                    System.out.println(plain.substring(0, Math.min(1000, plain.length())));
                                    System.out.println("--------------------------------------------------");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}