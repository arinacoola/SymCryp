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
        int l=cleanText.length();

        int[] counts33 = Frequencies.countLetterFrequencies(cleanText);
        double H1_33 = Entropy.calcH1(counts33, cleanText.length());

        int[][] bigrams33Intersecting= Frequencies.countBigramsIntersecting(cleanText);
        double H2_33_inter = Entropy.calcH2(bigrams33Intersecting, l-1);

        int [][] bigrams33No=Frequencies.countBigramsNoIntersecting(cleanText);
        double H2_33_no= Entropy.calcH2(bigrams33No, l/2);


        String cleanNoSpace = cleanText.replace(" ", "");
        int l1=cleanNoSpace.length();

        int[] counts32 = Frequencies.countLetterFrequenciesNoSpace(cleanNoSpace);
        double H1_32 = Entropy.calcH1(counts32, l1);

        int[][] bi32_inter = Frequencies.countBigramsIntersectingNoSpace(cleanNoSpace);
        double H2_32_inter = Entropy.calcH2(bi32_inter, l1 - 1);

        int[][] bi32_no = Frequencies.countBigramsNoIntersectingNoSpace(cleanNoSpace);
        double H2_32_no = Entropy.calcH2(bi32_no, l1 / 2);

        System.out.println("WITH SPACES:");
        System.out.println("H1 = " + H1_33);
        System.out.println("H2 inter = " + H2_33_inter);
        System.out.println("H2 non-inter = " + H2_33_no);

        System.out.println("\nNO SPACES:");
        System.out.println("H1 = " + H1_32);
        System.out.println("H2 inter = " + H2_32_inter);
        System.out.println("H2 non-inter = " + H2_32_no);

    }
}
