public class ConsecutiveStrings {
    public static void main(String[] args) {
        /*Instrucción
        You are given an array(list) strarr of strings and an integer k. Your task is to return the first longest string consisting of k consecutive strings taken in the array.
            
        Examples:
        strarr = ["tree", "foling", "trashy", "blue", "abcdef", "uvwxyz"], k = 2
            
        Concatenate the consecutive strings of strarr by 2, we get:
            
        treefoling   (length 10)  concatenation of strarr[0] and strarr[1]
        folingtrashy ("      12)  concatenation of strarr[1] and strarr[2]
        trashyblue   ("      10)  concatenation of strarr[2] and strarr[3]
        blueabcdef   ("      10)  concatenation of strarr[3] and strarr[4]
        abcdefuvwxyz ("      12)  concatenation of strarr[4] and strarr[5]
            
        Two strings are the longest: "folingtrashy" and "abcdefuvwxyz".
        The first that came is "folingtrashy" so 
        longest_consec(strarr, 2) should return "folingtrashy".
            
        In the same way:
        longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) --> "abigailtheta"
        n being the length of the string array, if n = 0 or k > n or k <= 0 return "" (return Nothing in Elm, "nothing" in Erlang).
            
        Note
        consecutive strings : follow one after another without an interruption
        */
    }
    public static String longestConsec(String[] strarr, int k) {
        if (strarr.length == 0 || k > strarr.length || k <= 0) {
            return "";
        }
        
        int longestStreak = 0;
        String longestWord = "";

        // Se ajusta el límite para evitar salir de los límites del arreglo
        for (int i = 0; i <= strarr.length - k; i++) {
            
            // Es altamente recomendable usar StringBuilder para concatenar en bucles
            StringBuilder concat = new StringBuilder();
            
            // Se ajusta la condición para tomar exactamente 'k' elementos
            for (int j = i; j < i + k; j++) {
                concat.append(strarr[j]);
            }
            
            String currentConsecutive = concat.toString();
            
            // Se evalúa si la nueva cadena es estrictamente mayor a la registrada
            if (currentConsecutive.length() > longestStreak) {
                longestStreak = currentConsecutive.length();
                longestWord = currentConsecutive;
            }
        }
        return longestWord;
    }
}

/*Retroalimentación
Para llevar el ejercicio `LongestConsec` a su máxima eficiencia, se debe aplicar el algoritmo de **Ventana Deslizante (Sliding Window)**. El enfoque anterior tiene una complejidad de $O(n \times k)$ porque concatena texto en cada paso; esta optimización lo reduce a $O(n)$ trabajando temporalmente solo con números enteros.

**Mecánica de la Ventana Deslizante:**

* **Contar sin concatenar:** Se calcula la suma de las longitudes de las primeras `k` palabras.
* **Deslizar:** Para evaluar el siguiente grupo, simplemente se resta la longitud de la palabra que queda atrás y se suma la longitud de la palabra nueva.
* **Construir al final:** Solo se realiza una única concatenación real (con `StringBuilder`) una vez que se ha identificado el índice exacto donde comienza la cadena más larga.

```java
class LongestConsec {
    public static String longestConsec(String[] strarr, int k) {
        int n = strarr.length;
        if (n == 0 || k > n || k <= 0) {
            return "";
        }

        int currentLengthSum = 0;
        int maxLengthSum = 0;
        int bestStartIndex = 0;

        // 1. Calcular la longitud del primer bloque de tamaño 'k'
        for (int i = 0; i < k; i++) {
            currentLengthSum += strarr[i].length();
        }
        maxLengthSum = currentLengthSum;

        // 2. Deslizar la ventana por el resto del arreglo
        for (int i = k; i < n; i++) {
            // Se actualiza la suma: + nueva palabra - palabra saliente
            currentLengthSum = currentLengthSum + strarr[i].length() - strarr[i - k].length();
            
            if (currentLengthSum > maxLengthSum) {
                maxLengthSum = currentLengthSum;
                bestStartIndex = i - k + 1;
            }
        }

        // 3. Construir la cadena ganadora una sola vez
        StringBuilder result = new StringBuilder();
        for (int i = bestStartIndex; i < bestStartIndex + k; i++) {
            result.append(strarr[i]);
        }

        return result.toString();
    }
}

```

*(Nota: Si en tu mensaje anterior olvidaste adjuntar el código de un ejercicio totalmente nuevo, por favor pégalo en tu siguiente respuesta).**/
