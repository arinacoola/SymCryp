import java.nio.file.Files;
import java.nio.file.Path;

public class Main3 {
    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("lab3/06.txt"));
        text = text.toLowerCase().replace('ё', 'е').replace('ъ', 'ь').replaceAll("[^а-я]", "");
        String alph = "абвгдежзийклмнопрстуфхцчшщьыэюя";
        int[][] bigrams = Freq.countBigramsAffine(text, alph);
        Freq.top5Bigrams(bigrams, alph);
    }
}
