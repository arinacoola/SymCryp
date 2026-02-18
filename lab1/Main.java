import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("bratia_karamazovy_-_fiedor_mikhailovich_dostoievskii.txt");
        String readFile = Files.readString(path, Charset.forName("windows-1251"));
        String cleanText = TextFilter.filter(readFile);

        int[] counts = Frequencies.countLetterFrequencies(cleanText);
        double H1 = Entropy.calcH1(counts, cleanText.length());

        int[][] bigrams = Frequencies.countBigramsIntersecting(cleanText);
        int n = cleanText.length() - 1;
        double H2 = Entropy.calcH2Intersecting(bigrams, n);

        System.out.println(cleanText.substring(0, 200));
        System.out.println("Clean Length: " + cleanText.length());
    }
}
