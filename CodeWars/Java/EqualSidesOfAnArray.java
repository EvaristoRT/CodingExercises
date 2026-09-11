public class EqualSidesOfAnArray {
    public static void main(String[] args) {
        /*Instrucción
        You are going to be given an array of integers. Your job is to take that array and find an index N where the sum of the integers to the left of N is equal to the sum of the integers to the right of N.

If there is no index that would make this happen, return -1.

For example:
Let's say you are given the array {1,2,3,4,3,2,1}:
Your function will return the index 3, because the sum of left side of the index ({1,2,3}) and the sum of the right side of the index ({3,2,1}) both equal 6.

Let's look at another one.
You are given the array {1,100,50,-51,1,1}:
Your function will return the index 1, because the sum of left side of the index ({1}) and the sum of the right side of the index ({50,-51,1,1}) both equal 1.

Last one:
You are given the array {20,10,-80,10,10,15,35}
At index 0 the left side is {}
The right side is {10,-80,10,10,15,35}
They both are equal to 0 when added. (Empty arrays are equal to 0 in this problem)
Index 0 is the place where the left side and right side are equal.

Note: Please remember that in most languages the index of an array starts at 0.

Input
An integer array of length 0 < arr < 1000. The numbers in the array can be any integer positive or negative.

Output
The lowest index N where the side to the left of N is equal to the side to the right of N. If you do not find an index that fits these rules, then you will return -1.

Note
If you are given an array with multiple answers, return the lowest correct index.
        */
    }

    public static int findEvenIndex(int[] arr) {
        int sumaTotal = 0;
        for(int numero : arr){
            sumaTotal += numero;
        }
        int sumaIzquierda = 0;
        for(int i = 0; i<=arr.length-1;i++){
            int sumaDerecha = sumaTotal - sumaIzquierda - arr[i];
            if (sumaIzquierda == sumaDerecha) {
                return i;
            }else{
                sumaIzquierda += arr[i];
            }
        }
        
        return -1;
    }
}
/*Retroaliimentación 
No existe ninguna forma de resolver esto en menos tiempo o usando menos memoria. Aquí está el análisis técnico de por qué tu solución tocó el techo de la optimización:Complejidad de Tiempo: O(N) (Tiempo Lineal)Para saber si un arreglo está equilibrado, estás obligado a leer todos los números al menos una vez; de lo contrario, te faltaría información. Tu código recorre el arreglo exactamente dos veces (una para la suma total y otra para evaluar el índice). Matemáticamente, $O(2N)$ se simplifica a $O(N)$. Es imposible vencer el tiempo lineal en este problema.Complejidad de Espacio: O(1) (Espacio Constante)No creaste arreglos secundarios, listas, ni mapas para guardar información temporal. Solo usaste tres variables primitivas (sumaTotal, sumaIzquierda y sumaDerecha). Sin importar si el arreglo tiene 10 elementos o 10 millones, tu programa consumirá exactamente la misma cantidad mínima de memoria RAM.Evaluación Temprana (Short-circuiting)Al colocar un return i; dentro del bucle, tu algoritmo se detiene en el instante exacto en que encuentra la respuesta. Si la respuesta está en el índice 1 de un arreglo de 1000 elementos, tu código no calculará los 999 restantes.El único "pero" (Nivel de refactorización visual)Dado que el algoritmo ya es perfecto, lo único que un Senior Developer te cambiaría en una revisión de código es limpiar un poco la sintaxis para que se vea más profesional. Cuando tienes un return dentro de un if, el bloque else es redundante.Así se vería tu misma solución óptima, pero con sintaxis de nivel experto:Javapublic static int findEvenIndex(int[] arr) {
    int sumaTotal = 0;
    for (int numero : arr) {
        sumaTotal += numero;
    }
    
    int sumaIzquierda = 0;
    for (int i = 0; i < arr.length; i++) {
        if (sumaIzquierda == sumaTotal - sumaIzquierda - arr[i]) {
            return i;
        }
        sumaIzquierda += arr[i];
    }
    
    return -1;
}
*/