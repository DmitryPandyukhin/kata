import java.util.Objects;

public class  RomanDigit {
    static int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    static String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    /**
     * Метод преобразует символ в римскую цифру.
     * @param romanDigit Предполагаемая римская цифра.
     * @return Найденное в перечислении соответствие римской цифре или null, если соответствия не найдено.
     */
    static int getRomanDigit(char romanDigit){
        String findStr = String.valueOf(romanDigit);
        for(int i = 0; i < romanLiterals.length; i++){
            if(Objects.equals(romanLiterals[i], findStr))
                return values[i];
        }
        return 0;
    }
}
