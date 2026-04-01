import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main3 {
    public static void main(String[] args) throws Exception {
        String alph = "абвгдежзийклмнопрстуфхцчшщьыэюя";

        String cipherText = Files.readString(Path.of("lab3/06.txt"));
        cipherText = cipherText.toLowerCase()
                .replace('ё', 'е')
                .replace('ъ', 'ь')
                .replaceAll("[^а-я]", "");

        List<KeyFind.Key> keys = KeyFind.findKeys("но", "то", "хе", "ще", alph);

        for (KeyFind.Key key : keys) {
            String plain = AffineDecrypt.decrypt(cipherText, key.a, key.b, alph);
            System.out.println("key = " + key);
            System.out.println(plain.substring(0, 1000));
            System.out.println("--------------------------------------------------");
        }
    }
}