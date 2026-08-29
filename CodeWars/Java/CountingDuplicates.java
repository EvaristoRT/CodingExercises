import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CountingDuplicates {
    public static void main(String[] args) {
        /*Instrucción
            Count the number of Duplicates
            Write a function that will return the count of distinct case-insensitive alphabetic characters and numeric digits that occur more than once in the input string. The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.
            
            Example
            "abcde" -> 0 # no characters repeats more than once
            "aabbcde" -> 2 # 'a' and 'b'
            "aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
            "indivisibility" -> 1 # 'i' occurs six times
            "Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
            "aA11" -> 2 # 'a' and '1'
            "ABBA" -> 2 # 'A' and 'B' each occur twice
        */
    }
    public static int duplicateCount(String text) {
        text = text.toLowerCase();
        HashMap <Character, Integer> counting = new HashMap<>();
        Set <Character> duplicates = new HashSet<>();
        for(char letter : text.toCharArray()){
            counting.put(letter, counting.getOrDefault(letter, 0)+1);
        }
        for(char letter : text.toCharArray()){
            if (counting.get(letter)>1) {
                duplicates.add(letter);
            }
        }
        return duplicates.size();
    }
}
/*Retroalimentación
La solución planteada es correcta, cumple perfectamente con los requerimientos del problema y maneja adecuadamente la insensibilidad a mayúsculas y minúsculas mediante toLowerCase(). El uso de getOrDefault es una excelente práctica para evitar verificaciones de nulos.

A continuación, se presentan algunas observaciones para optimizar el rendimiento y reducir el consumo de memoria:

Iteraciones redundantes y uso de memoria
Actualmente se llama a text.toCharArray() dos veces. Cada llamada crea un nuevo arreglo de caracteres en la memoria, lo cual es ineficiente en términos de espacio. Además, el segundo bucle itera nuevamente sobre toda la cadena de texto, lo que no es necesario.

Eliminación del HashSet
El uso del HashSet se puede omitir por completo. Dado que el HashMap ya contiene cada carácter único como clave (junto con su frecuencia total como valor), es más óptimo iterar directamente sobre los valores del mapa una vez que se han contado todos los caracteres.

Solución iterativa optimizada
Al iterar directamente sobre los valores del HashMap (counting.values()), se evita la creación del HashSet y la segunda llamada a toCharArray():

Java
import java.util.HashMap;

public static int duplicateCount(String text) {
    text = text.toLowerCase();
    HashMap<Character, Integer> counting = new HashMap<>();
    
    // Se crea el arreglo de caracteres una sola vez
    for (char letter : text.toCharArray()) {
        counting.put(letter, counting.getOrDefault(letter, 0) + 1);
    }
    
    int duplicates = 0;
    // Se itera únicamente sobre las frecuencias únicas obtenidas
    for (int count : counting.values()) {
        if (count > 1) {
            duplicates++;
        }
    }
    
    return duplicates;
}
*/