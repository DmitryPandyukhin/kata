/**
 * Тип числа: арабское или римское.
 */
public enum NumberType {
    Arabic(1), Roman(2);
    int num;
    NumberType(int num){
        this.num = num;
    }
}
