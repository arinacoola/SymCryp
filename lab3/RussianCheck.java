public class RussianCheck {

    public static int score(String text) {
        if (text == null || text.length() < 100) {
            return -1000;
        }
        int score = 0;


        if (hasBadBigrams(text)) {
            score -= 10;
        }

        score += freqScore(text, 'о', 0.06);
        score += freqScore(text, 'е', 0.05);
        score += freqScore(text, 'а', 0.04);
        score += freqScore(text, 'и', 0.04);
        score += freqScore(text, 'н', 0.04);


        if (freq(text, 'ф') < 0.02){
            score += 1;
        }
        if (freq(text, 'щ') < 0.01) {
            score += 1;
        }

        score += countOverlap(text, "ст");
        score += countOverlap(text, "но");
        score += countOverlap(text, "то");
        score += countOverlap(text, "на");
        score += countOverlap(text, "ен");

        if (text.contains("что")) {
            score += 5;
        }
        if (text.contains("это")){
            score += 5;
        }
        if (text.contains("его")){
            score += 4;
        }
        if (text.contains("она")){
            score += 4;
        }
        if (text.contains("как")) {
            score += 3;
        }
        if (text.contains("про")) {
            score += 3;
        }
        return score;
    }

    public static boolean isMeaningful(String text) {
        return score(text) >= 15;
    }

    private static int freqScore(String text, char c, double threshold) {
        return freq(text, c) > threshold ? 2 : 0;
    }

    private static double freq(String text, char c) {
        int cnt = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == c) cnt++;
        }
        return (double) cnt / text.length();
    }

    private static int countOverlap(String text, String bg) {
        int cnt = 0;
        for (int i = 0; i + 1 < text.length(); i++) {
            if (text.substring(i, i + 2).equals(bg)) {
                cnt++;
            }
        }
        return Math.min(cnt, 3);
    }

    private static boolean hasBadBigrams(String text) {
        String[] bad = {"аы","оы","уы","эы","ыы","аь","оь","уь","эь","йй","щщ"};
        for (String b : bad) {
            if (text.contains(b)) return true;
        }
        return false;
    }
}