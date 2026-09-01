public class PlayingWithDigits {
    public static void main(String[] args) {
        /*Instrucción
        Some numbers have funny properties. For example:

        89 --> 8¹ + 9² = 89 * 1
        695 --> 6² + 9³ + 5⁴= 1390 = 695 * 2
        46288 --> 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
        Given two positive integers n and p, we want to find a positive integer k, if it exists, such that the sum of the digits of n raised to consecutive powers starting from p is equal to k * n.

        In other words, writing the consecutive digits of n as a, b, c, d ..., is there an integer k such that :
        (ap+bp+1+cp+2+dp+3+...)=n∗k(a p +b p+1 +c p+2 +d p+3 +...)=n∗k
        If it is the case we will return k, if not return -1.

        Note: n and p will always be strictly positive integers.

        Examples:
        n = 89; p = 1 ---> 1 since 8¹ + 9² = 89 = 89 * 1

        n = 92; p = 1 ---> -1 since there is no k such that 9¹ + 2² equals 92 * k

        n = 695; p = 2 ---> 2 since 6² + 9³ + 5⁴= 1390 = 695 * 2

        n = 46288; p = 3 ---> 51 since 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
        */
    }
    public static long digPow(int n, int p) {
		char[] numbers = String.valueOf(n).toCharArray();
        long sum = 0;
        for(int i = 0; i < numbers.length; i++){
            int digito = numbers[i] - '0';
            sum +=(long) Math.pow(digito, p+i);
        }
        if (sum % n == 0) {
            return sum/n;
        }else{
            return -1;
        }
	}
}
/*Retroalimentación
El código actual es correcto, robusto y ha integrado exitosamente todas las correcciones previas. Resuelve el problema de manera precisa, evitando riesgos de desbordamiento numérico o cálculos erróneos basados en tablas ASCII. Es una solución excelentemente equilibrada entre legibilidad y eficacia.

A continuación, se presentan algunas observaciones menores enfocadas estrictamente en la limpieza y las buenas prácticas de escritura:

* **Importaciones innecesarias:** Se detecta la instrucción `import java.util.ArrayList;` al inicio del archivo. Dado que no se utiliza ninguna lista dinámica en la lógica, es recomendable eliminar esta línea. Los entornos de desarrollo suelen marcar esto como una advertencia (warning) de código inactivo.
* **Simplificación del retorno (Operador Ternario):** El bloque `if-else` del final cumple su función perfectamente, pero puede condensarse en una única línea elegante utilizando el operador ternario `? :`. Esta es una práctica muy estandarizada en Java para condiciones de retorno simples.
```java
return (sum % n == 0) ? (sum / n) : -1;

```


* **Claridad vs Rendimiento (Opcional):** La operación `numbers[i] - '0'` es la técnica más eficiente a nivel de procesamiento para obtener el entero. No obstante, es útil saber que Java cuenta con un método integrado que hace exactamente lo mismo y resulta más descriptivo para programadores junior que lean el código en el futuro:
```java
int digito = Character.getNumericValue(numbers[i]);

```



El código refactorizado aplicando el operador ternario y limpiando las importaciones quedaría así:

```java
public class PlayingWithDigits {
    public static long digPow(int n, int p) {
        char[] numbers = String.valueOf(n).toCharArray();
        long sum = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            int digito = numbers[i] - '0';
            sum += (long) Math.pow(digito, p + i);
        }
        
        return (sum % n == 0) ? (sum / n) : -1;
    }
}

```
*/
