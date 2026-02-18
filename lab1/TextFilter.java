public class TextFilter {
        public static String filter(String rawText) {
                StringBuilder builder = new StringBuilder();
                boolean lastWasSpace = true;
                for (char c : rawText.toCharArray()) {
                        c = Character.toLowerCase(c);
                        if(c=='ё'){
                                c ='е';
                        }
                        if(c=='ъ'){
                                c='ь';
                        }
                        if (!(c >= 'а' && c <= 'я')) {
                                c = ' ';
                        }
                        if (c == ' ') {
                                if (!lastWasSpace) {
                                        builder.append(c);
                                        lastWasSpace = true;
                                }
                        }
                        else {
                                builder.append(c);
                                lastWasSpace = false;
                        }
                }
                String cleanText = builder.toString().trim();
                return cleanText;
        }
}



