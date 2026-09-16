public class BitCounting {
    public static void main(String[] args) {
        /*Intruscción
        Write a function that takes an integer as input, and returns the number of bits that are equal to one in the binary representation of that number. You can guarantee that input is non-negative.

        Example: The binary representation of 1234 is 10011010010, so the function should return 5 in this case
        */
    }

    public static int countBits(int n){
		int bits = 0;

        while (n>0) {
            if (n%2 == 1) {
                bits++;
            }
            n = n/2;
        }

        return bits;
	}
}
/*Retroalimentación
Tu solución es **correcta, eficiente y bastante limpia** para el problema. De hecho, estás usando directamente la lógica matemática de la representación binaria, sin convertir el número a `String`, lo cual es una buena decisión.

### 1. Lógica

Tu parte principal:

```java
while (n > 0) {
    if (n % 2 == 1) {
        bits++;
    }
    n = n / 2;
}
```

Funciona porque:

* `n % 2` te dice si el bit menos significativo es `1` o `0`.

  * `n % 2 == 1` → el bit es `1`.
  * `n % 2 == 0` → el bit es `0`.
* `n / 2` elimina ese último bit y permite revisar el siguiente.

Por ejemplo, con `1234`:

```text
1234 % 2 = 0
617  % 2 = 1  ← cuenta
308  % 2 = 0
154  % 2 = 0
77   % 2 = 1  ← cuenta
38   % 2 = 0
19   % 2 = 1  ← cuenta
9    % 2 = 1  ← cuenta
4    % 2 = 0
2    % 2 = 0
1    % 2 = 1  ← cuenta

Total = 5
```

Y efectivamente:

```text
1234 = 10011010010
          ↑ ↑  ↑↑  ↑
          5 unos
```

### 2. Caso `n = 0`

También lo manejas correctamente.

Si:

```java
countBits(0)
```

el `while` nunca se ejecuta y `bits` permanece en `0`.

Eso es correcto porque:

```text
0 = 00000000...
```

y tiene **0 bits con valor 1**.

### 3. Complejidad

Tu solución tiene:

* **Tiempo:** `O(log n)`
* **Espacio:** `O(1)`

Esto es bueno porque recorres los bits del número y no necesitas crear estructuras adicionales.

Además, evitas una solución como:

```java
Integer.toBinaryString(n)
```

seguida de recorrer el `String`. Esa alternativa también sería válida, pero tu implementación demuestra que entiendes **cómo funciona la representación binaria**, en lugar de delegarlo a una función de Java.

### 4. Un pequeño detalle de estilo

Podrías escribir:

```java
while (n > 0) {
    if (n % 2 == 1) {
        bits++;
    }
    n /= 2;
}
```

`n /= 2` es simplemente una forma más compacta de:

```java
n = n / 2;
```

No cambia absolutamente nada en cuanto a funcionamiento.

---

### 5. ¿Es la solución más óptima?

**Para una solución basada en la lógica que estás aprendiendo, sí, es muy buena.**

Sin embargo, Java tiene una operación específica para contar bits:

```java
Integer.bitCount(n)
```

También existe el algoritmo conocido como **Brian Kernighan**, que aprovecha la operación:

```java
n &= (n - 1);
```

para eliminar directamente un `1` de la representación binaria en cada iteración.

Por ejemplo, conceptualmente:

```text
10110100
   ↓ n & (n-1)
10110000
   ↓
10100000
   ↓
10000000
   ↓
00000000
```

La ventaja es que el número de iteraciones depende de **cuántos `1` hay**, no del número total de bits.

Pero para este Codewars, **no necesitas complicarlo**. Tu solución es clara, correcta y tiene una complejidad excelente.

### Veredicto

| Aspecto          | Evaluación                    |
| ---------------- | ----------------------------- |
| Correctitud      | ✅ Correcta                    |
| Casos límite     | ✅ `0` funciona                |
| Complejidad      | ✅ `O(log n)`                  |
| Memoria          | ✅ `O(1)`                      |
| Legibilidad      | ✅ Muy buena                   |
| Uso de conceptos | ✅ Demuestras entender binario |
| Optimización     | 🟢 Buena                      |
| Posible mejora   | `n /= 2` por estilo           |

**Nivel de la solución: buena para tu etapa actual de Java.** Lo más importante aquí es que no solamente llegaste al resultado: utilizaste las propiedades de división y módulo para recorrer los bits, que es precisamente el concepto que el ejercicio busca practicar.

*/