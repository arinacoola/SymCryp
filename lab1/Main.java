import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("lab1/bratia_karamazovy_-_fiedor_mikhailovich_dostoievskii.txt");
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


        BufferedWriter wrtr = new BufferedWriter(new FileWriter("lab1/frequencies.txt"));
        wrtr.write("WITH SPACES:\n");
        frequencyOut(wrtr, counts33, l, true);
        wrtr.write("BIGRAMS INTERSECTING:\n");
        bigramOut(wrtr, bigrams33Intersecting, l - 1, true);
        wrtr.write("BIGRAMS NON-INTERSECTING:\n");
        bigramOut(wrtr, bigrams33No, l / 2, true);

        wrtr.write("NO SPACES:\n");
        frequencyOut(wrtr, counts32, l1, false);
        wrtr.write("BIGRAMS INTERSECTING:\n");
        bigramOut(wrtr, bi32_inter, l1 - 1, false);
        wrtr.write("BIGRAMS NON-INTERSECTING:\n");
        bigramOut(wrtr, bi32_no, l1 / 2, false);
        wrtr.close();
    }

    public static void frequencyOut(BufferedWriter wrtr, int[] counts,int total, boolean space) throws IOException {
        wrtr.write("symbol \tnum \tfrequency\n");
        boolean[] use = new boolean[counts.length];
        for (int i = 0; i < counts.length; i++) {
            int maxInd = -1;
            for (int j = 0; j < counts.length; j++) {
                if (!use[j] && counts[j] > 0) {
                    if (maxInd == -1 || counts[j] > counts[maxInd]) {
                        maxInd = j;
                    }
                }
            }
            if (maxInd == -1) {
                break;
            }
            use[maxInd] = true;
            char c;
            if (space && maxInd == 32){
                c = ' ';
            }
            else{
                c = (char) ('а' + maxInd);
            }
            double p = (double) counts[maxInd] / total;
            if (c == ' ') {
                wrtr.write("space\t" + counts[maxInd] + "\t" + String.format("%.8f", p) + "\n");
            }
            else {
                wrtr.write(c + "\t" + counts[maxInd] + "\t" + String.format("%.8f", p) + "\n");
            }
        }
        wrtr.write("\n");
    }

    public static void bigramOut(BufferedWriter wrtr,int[][] bigrams, int total,boolean space) throws IOException {
        int n = bigrams.length;
        wrtr.write("(frequency matrix)\n");
        wrtr.write("     ");
        for (int i = 0; i < n; i++) {
            if (space && i == 32) {
                wrtr.write(String.format("%8s", "_"));
            }
            else {
                wrtr.write(String.format("%8c", (char) ('а' + i)));
            }
        }
        wrtr.write("\n");
        for (int i = 0;i < n;i++) {
            if (space && i == 32) {
                wrtr.write(String.format("%4s ", "_"));
            }
            else {
                wrtr.write(String.format("%4c ", (char) ('а' + i)));
            }
            for (int j = 0; j < n; j++) {
                double p = (double) bigrams[i][j] / total;
                wrtr.write(String.format("%8.6f", p));
            }
            wrtr.write("\n");
        }
        wrtr.write("\n");
    }
}
