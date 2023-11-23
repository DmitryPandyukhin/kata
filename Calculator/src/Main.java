import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        calc(input);
    }

    /**
     * Метод выполняет одну из указанных арифметических операций: '+', '-', '*', '/'.
     * @param input Строка с арифметическим выражением между двумя числами.
     *              Числа могут быть только римскими или только арабскими.
     * @return Результат арифметический операции.
     */
    public static String calc(String input) {
        try {
            Pattern pat1 = Pattern.compile(" *[0-9]+ *[+\\-*/] *[0-9]+ *");
            Matcher mat1 = pat1.matcher(input);

            NumberType numberType;
            // Проверка формата операции.
            if(!mat1.matches()){
                Pattern pat2 = Pattern.compile(" *[ivxlcdmIVXLCDM]+ *[+\\-*/] *[ivxlcdmIVXLCDM]+ *");
                Matcher mat2 = pat2.matcher(input);

                if(!mat2.matches()){
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *);");
                }
                // Проверка корректности римскоих чисел.
                else{
                    int maxLen = 0;

                    Matcher mat3 = Pattern.compile("([ivxlcdmIVXLCDM])\\1+").matcher(input);
                    while (mat3.find()) {
                        String sub = mat3.group();

                        if (sub.length() > maxLen) {
                            maxLen = sub.length();
                        }
                  }
                  if(maxLen > 3)
                      throw new Exception("Некорректно задано римское число, " +
                              "т.к. количество последовательно повторяющихся цифр должно быть не больше 3.");

                  numberType = NumberType.Roman;
              }
            }
            else {
                numberType = NumberType.Arabic;
            }

            // Определим арифметическую операцию.
            int operationIndex = input.indexOf('+');
            if(operationIndex == -1) operationIndex = input.indexOf('-');
            if(operationIndex == -1) operationIndex = input.indexOf('*');
            if(operationIndex == -1) operationIndex = input.indexOf('/');
            if(operationIndex == -1) // Этот код возможно избыточен.
                throw new Exception("Арифметическая операция не найдена.");

            // Заполним переменные чисел
            String num1 = input.substring(0, operationIndex - 1).trim();
            String num2 = input.substring(operationIndex + 1).trim();

            int num1Int = 0;
            int num2Int = 0;
            // Римские цифры преобрабуем в арабские для выполнения арифменическкой операции.
            if(numberType == NumberType.Roman){
                num1Int = ArabicNumber.toArabicNumber(num1.toUpperCase());
                num2Int = ArabicNumber.toArabicNumber(num2.toUpperCase());
            }

            // Арабские цифры преобразуем в нужный тип.
            if(numberType == NumberType.Arabic){
                num1Int = Integer.parseInt(num1);
                num2Int = Integer.parseInt(num2);
            }

            // Контроль интервала операндов.
            if((num1Int > 10) || (num1Int < 1) || (num2Int > 10) || (num2Int < 1))
                throw new Exception("Значение операнда находится за пределами допустимого интервала [1, 10].");

            // Выполняем арифметическую операцию.
            int result = 0;
            switch (input.charAt(operationIndex)){
                case '+' -> result = num1Int + num2Int;
                case '-' -> result = num1Int - num2Int;
                case '*' -> result = num1Int * num2Int;
                case '/' -> result = num1Int / num2Int;
            }

            // Проверяем корреерктность результата.
            if(numberType == NumberType.Roman){
                if(result < 0)
                {
                    throw new Exception("Результат не может быть отрицательным.");
                }

                return RomanNumber.toRomanNumber(result);
            }

            // Если результат - арабское число.
            return String.valueOf(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
