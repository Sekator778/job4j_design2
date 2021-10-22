package codewars;

public class DigitalRoot {
    public static int digitalRoot(int n) {
        if (String.valueOf(n).length() > 1) {
            int sum = 0;
            String numAsSum = String.valueOf(n);

            for (int i = 0; i < numAsSum.length(); i++) {
                int digit = Character.getNumericValue(numAsSum.charAt(i));
                sum = sum + digit;
            }
            return digitalRoot(sum);
        } else {
            return n;
        }
    }
}