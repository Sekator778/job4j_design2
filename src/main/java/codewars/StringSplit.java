package codewars;

public class StringSplit {
    public static String[] solution(String s) {
        int x = 0;
        if ((s.length() % 2) != 0) {
            x++;
        }
        int size = s.length() / 2 + x;
        String[] result = new String[size];
        String current;
        int k = 0;
        for (int i = 0; i < s.length(); i = i + 2) {
            if ((i + 1) == s.length()) {
                current = s.charAt(i) + "_";
            } else {
                current = Character.toString(s.charAt(i)) + s.charAt(i + 1);
            }
            result[k++] = current;
        }
        return result;
    }
}