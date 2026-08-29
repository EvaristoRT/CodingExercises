public class ProductOfConsecutiveFibNumbers {
    public static void main(String[] args) {
        /*Instrucción
        The Fibonacci numbers are the numbers in the following integer sequence (Fn): 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, ...

        such that:

        F(0)=0F(1)=1F(n)=F(n−1)+F(n−2)
        F(0)=0
        F(1)=1
        F(n)=F(n−1)+F(n−2)
        Given a number, say prod (for product), we search two Fibonacci numbers F(n) and F(n+1) verifying:

        F(n)∗F(n+1)=prod
        F(n)∗F(n+1)=prod
        Your function takes an integer (prod) and returns an array/tuple (check the function signature/sample tests for the return type in your language):

        if F(n) * F(n+1) = prod:
        (F(n), F(n+1), true)
        If you do not find two consecutive F(n) verifying F(n) * F(n+1) = prod:
        (F(n), F(n+1), false)
        where F(n) is the smallest one such as F(n) * F(n+1) > prod.
        Examples:
        714 ---> (21, 34, true)
        --> since F(8) = 21, F(9) = 34 and 714 = 21 * 34

        800 --->  (34, 55, false)
        --> since F(8) = 21, F(9) = 34, F(10) = 55 and 21 * 34 < 800 < 34 * 55
        */
    }
    public static long[] productFib(long prod) {
        for(int i = 0; i < prod; i++){
            if ((fib(i) * fib(i+1)) > prod) {
                return new long[]{fib(i),fib(i+1),0};
            }else if((fib(i) * fib(i+1)) == prod){
                return new long[]{fib(i),fib(i+1),1};
            }
        }
        return null;
    }
    public static int fib(int n){
        if (n == 0) {
            return 0;
        }else if (n == 1) {
            return 1;
        }else{
            return fib(n - 1) + fib(n - 2);
        }
    }
}
/*Retroalimentación
El código implementa la lógica de validación correctamente, pero presenta limitaciones críticas de rendimiento y de tipos de datos que causarán errores (Time Limit Exceeded y desbordamiento de memoria) al evaluarse con valores grandes.Problemas críticos detectadosComplejidad exponencial $O(2^n)$: El método fib(int n) utiliza recursividad pura. Esto significa que para calcular un número, recalcula repetidamente toda la secuencia anterior. Al llamar a esta función dos (o hasta cuatro) veces dentro de cada iteración del bucle for, el programa colapsará rápidamente debido a la sobrecarga de procesamiento.Desbordamiento de enteros (Integer Overflow): El método fib está definido para devolver un tipo int. Los números de Fibonacci crecen de manera exponencial y superan el límite máximo de un entero de 32 bits (2,147,483,647) apenas en la posición n = 47. Como el problema exige el uso de long, los cálculos internos generarán números negativos erróneos al desbordarse.Límite del bucle ineficiente: El bucle for (int i = 0; i < prod; i++) itera hasta el valor del producto. Esto es innecesario, ya que el producto de los números de Fibonacci supera a prod en un número de pasos considerablemente menor.
Comparativa estructural
CaracterísticaCódigo actual 
(Recursivo)Código óptimo (Iterativo)Complejidad de Tiempo$O(2^n)$ - Muy deficiente$O(n)$ - Altamente eficienteComplejidad de Espacio$O(n)$ - Por la pila de llamadas$O(1)$ - Solo dos variablesTipo de Datoint (Riesgo de desbordamiento)long (Seguro para grandes cifras)
Implementación óptima recomendada
La forma estándar y más eficiente de resolver este problema es calcular la secuencia iterativamente en un solo recorrido, almacenando únicamente los dos valores actuales. Esto elimina la necesidad de un método auxiliar y reduce drásticamente el uso de recursos.Javapublic class ProductOfConsecutiveFibNumbers {
    public static long[] productFib(long prod) {
        long f1 = 0;
        long f2 = 1;
        
        // Se avanza en la secuencia mientras el producto sea menor a 'prod'
        while (f1 * f2 < prod) {
            long next = f1 + f2;
            f1 = f2;
            f2 = next;
        }
        
        // Se evalúa si el producto exacto se encontró para retornar 1 o 0
        return new long[]{ f1, f2, (f1 * f2 == prod) ? 1 : 0 };
    }
}
*/