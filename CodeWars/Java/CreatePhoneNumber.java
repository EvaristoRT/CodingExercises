
public class CreatePhoneNumber{
    public static void main(String[] args){
        /*
            Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
            Example
            Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
            The returned format must be correct in order to complete this challenge.
            Don't forget the space after the closing parentheses!
        */
        String number = createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        System.out.println(number);

    }
    public static String createPhoneNumber(int[] numbers) {
        String finalNumber = "(";

        for(int i = 0; i<3; i++){
            finalNumber = finalNumber + numbers[i];
        }
        finalNumber = finalNumber + ") " + numbers[3] + numbers[4] + numbers[5];
        finalNumber = finalNumber + "-" + numbers[6] + numbers[7] + numbers[8] + numbers[9];
        return finalNumber;
    }
}

/*
OBSERVACIONES

Una solución más limpia: StringBuilder
En Java, cuando vas construyendo un String poco a poco, StringBuilder es una mejor herramienta:
public static String createPhoneNumber(int[] numbers) {
    StringBuilder phone = new StringBuilder();

    phone.append("(");

    for (int i = 0; i < 3; i++) {
        phone.append(numbers[i]);
    }

    phone.append(") ");

    for (int i = 3; i < 6; i++) {
        phone.append(numbers[i]);
    }

    phone.append("-");

    for (int i = 6; i < 10; i++) {
        phone.append(numbers[i]);
    }

    return phone.toString();
}

Esto produce:

(123) 456-7890

La ventaja es que estás expresando mejor la estructura:

( + primeros 3 + ) + siguientes 3 + - + últimos 4

Una solución todavía más corta

Java permite usar String.format():

public static String createPhoneNumber(int[] numbers) {
    return String.format("(%d%d%d) %d%d%d-%d%d%d%d",
            numbers[0], numbers[1], numbers[2],
            numbers[3], numbers[4], numbers[5],
            numbers[6], numbers[7], numbers[8], numbers[9]);
}

Esta probablemente es la que mejor representa el problema, porque literalmente estás diciendo:

(%d%d%d) %d%d%d-%d%d%d%d

Es decir:

(123) 456-7890
*/