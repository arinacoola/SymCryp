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

        int[][] bigramsIntersecting= Frequencies.countBigramsIntersecting(cleanText);
        int n1 = cleanText.length() - 1;
        double H2inter = Entropy.calcH2(bigramsIntersecting, n1);

        int [][] bigramsNo=Frequencies.countBigramsNoIntersecting(cleanText);
        int n2 = cleanText.length() / 2;
        double H2no= Entropy.calcH2(bigramsNo, n2);

        System.out.println("H1 = " + H1);
        System.out.println("H2 (intersecting) = " + H2inter);
        System.out.println("H2 (non-intersecting) = " + H2no);
    }
}
