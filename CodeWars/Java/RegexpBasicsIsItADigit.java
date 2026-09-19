public class RegexpBasicsIsItADigit {
    public static void main(String[] args) {
        /*Instrucción
        Implement String#digit? (in Java StringUtils.isDigit(String)), which should return true if given object is a single digit (0-9), false otherwise.
        */
    }
    public static boolean isDigit(String s) {
        return s.matches("^\\d{1}$");
    }
}
