public class RomanNumber{
    public static String toRomanNumber(int arabicNumber) {
        StringBuilder roman = new StringBuilder();

        for(int i=0;i<RomanDigit.values.length;i++) {
            while(arabicNumber >= RomanDigit.values[i]) {
                arabicNumber -= RomanDigit.values[i];
                roman.append(RomanDigit.romanLiterals[i]);
            }
        }

        return roman.toString();
    }
}
