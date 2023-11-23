public class ArabicNumber{
    /**
     * Метод конвертирует римское число в арабское.
     * @param romanNumber Римское число.
     * @return Арабское число.
     */
    public static int toArabicNumber(String romanNumber) {
        char ch;
        int arabicDigit = 0;
        int arabicPreviousDigit = 0;
        int result = 0;

        for(int i = romanNumber.length() - 1; i >= 0; i--){
            ch = romanNumber.charAt(i);

            arabicDigit = RomanDigit.getRomanDigit(ch);

            if(arabicDigit >= arabicPreviousDigit){
                result += arabicDigit;
            }
            else{
                result -= arabicDigit;
            }
            arabicPreviousDigit = arabicDigit;
        }
        return result;
    }
}
