import java.util.HashMap;
import java.util.Map;

public class FindTheOddInt {
    public static void main(String[] args) {
        /*Instrucción
        Given an array of integers, find the one that appears an odd number of times.

        There will always be only one integer that appears an odd number of times.
            
        Examples
        [7] should return 7, because it occurs 1 time (which is odd).
        [0] should return 0, because it occurs 1 time (which is odd).
        [1,1,2] should return 2, because it occurs 1 time (which is odd).
        [0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
        [1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).
        */
    }
    public static int findIt(int[] a) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for(int number : a){
            if (hash.containsKey(number)) {
                hash.put(number, hash.get(number)+1);
                continue;
            }
            hash.put(number, 1);
        }
        for(Map.Entry<Integer,Integer> entry : hash.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if (value % 2 != 0) {
                return key;
            }
        }
        return 0;
    }
}
/*Retroalimentación
Tu solución está **bien planteada y es correcta** para el problema. De hecho, para alguien que está practicando `HashMap`, es una muy buena solución porque estás usando el mapa exactamente para lo que sirve: **contar cuántas veces aparece cada número**.

Pero sí hay algunos puntos que puedes mejorar, especialmente uno importante: **tu solución no es la más óptima posible en complejidad espacial**.

### 1. Tu lógica actual

Haces dos recorridos:

```java
for(int number : a){
    if (hash.containsKey(number)) {
        hash.put(number, hash.get(number)+1);
        continue;
    }
    hash.put(number, 1);
}
```

Primero construyes algo como:

```text
[1, 2, 2, 3, 3, 3, 4]

HashMap:
1 → 1
2 → 2
3 → 3
4 → 1
```

Y después:

```java
for(Map.Entry<Integer,Integer> entry : hash.entrySet()){
```

buscas el que tenga una cantidad impar.

Eso funciona perfectamente.

---

## 2. Complejidad

Tu solución tiene:

* **Tiempo:** `O(n)`
* **Espacio:** `O(n)`

¿Por qué `O(n)` de tiempo?

Porque recorres el arreglo una vez y posteriormente recorres el `HashMap`. En el peor caso, el mapa puede tener `n` elementos:

```text
n elementos del array
        ↓
O(n)

n elementos del HashMap
        ↓
O(n)

O(n) + O(n) = O(n)
```

Eso está bastante bien.

El problema está en el espacio:

```java
HashMap<Integer, Integer> hash = new HashMap<>();
```

Estás almacenando potencialmente todos los números diferentes del arreglo.

---

# 3. Existe una solución mucho más óptima: XOR

Este ejercicio de Codewars tiene una característica muy importante:

> **Siempre habrá exactamente un número que aparece una cantidad impar de veces.**

Eso permite utilizar el operador XOR (`^`).

Por ejemplo:

```text
7 ^ 7 = 0
```

y:

```text
7 ^ 7 ^ 7 = 7
```

Esto ocurre porque XOR tiene estas propiedades:

```text
x ^ x = 0
x ^ 0 = x
```

Por lo tanto, los números que aparecen un número **par** de veces se cancelan entre sí.

Por ejemplo:

```text
[1, 2, 2, 3, 3, 3, 4, 3, 3, 3, 2, 2, 1]
```

Si haces XOR de todos:

```text
1 ^ 2 ^ 2 ^ 3 ^ 3 ^ 3 ^ 4 ^ 3 ^ 3 ^ 3 ^ 2 ^ 2 ^ 1
```

Puedes agrupar:

```text
1 ^ 1 = 0

2 ^ 2 ^ 2 ^ 2 = 0

3 ^ 3 ^ 3 ^ 3 ^ 3 ^ 3 = 0

4 = 4
```

Resultado:

```text
4
```

Por eso una solución basada en XOR puede tener:

* **Tiempo:** `O(n)`
* **Espacio:** `O(1)` ← aquí está la gran mejora.

---

## 4. Pero no consideraría tu solución "mala"

Al contrario. Para tu etapa actual de aprendizaje, **me gusta tu solución** porque demuestra que sabes utilizar:

* `HashMap`
* `containsKey()`
* `put()`
* `get()`
* `entrySet()`
* `Map.Entry`
* recorrido con `for-each`

Y además identificaste correctamente el patrón:

> "Necesito saber cuántas veces aparece cada elemento → `HashMap`."

Eso es una buena asociación.

Lo que yo haría es **primero dominar tu solución y después aprender XOR**, porque XOR es más específico y puede parecer un truco si todavía no tienes claras sus propiedades.

---

# 5. Hay una pequeña mejora en tu código

Esta parte:

```java
if (hash.containsKey(number)) {
    hash.put(number, hash.get(number)+1);
    continue;
}
hash.put(number, 1);
```

se puede simplificar utilizando `getOrDefault()`:

```java
hash.put(number, hash.getOrDefault(number, 0) + 1);
```

Tu ciclo completo quedaría:

```java
for (int number : a) {
    hash.put(number, hash.getOrDefault(number, 0) + 1);
}
```

Es más compacto y expresa directamente:

> "Obtén la cantidad actual; si no existe, usa 0; después súmale 1."

---

### Un detalle más

Tu:

```java
return 0;
```

al final realmente **no debería ser necesario conceptualmente**, porque el enunciado garantiza que siempre existe un número con cantidad impar.

Pero Java necesita que el método tenga un `return` en todos los caminos posibles, así que necesitas algún retorno final si mantienes esa estructura.

---

## Mi evaluación

| Aspecto                | Evaluación |
| ---------------------- | ---------- |
| Correctitud            | ⭐⭐⭐⭐⭐      |
| Uso de `HashMap`       | ⭐⭐⭐⭐⭐      |
| Legibilidad            | ⭐⭐⭐⭐☆      |
| Complejidad temporal   | ⭐⭐⭐⭐⭐      |
| Complejidad espacial   | ⭐⭐⭐☆☆      |
| Optimización           | ⭐⭐⭐⭐☆      |
| Adecuada para aprender | ⭐⭐⭐⭐⭐      |

**Conclusión:** tu solución es **correcta y tiene una complejidad temporal óptima `O(n)`**, pero existe una solución mejor en espacio mediante XOR (`O(1)`). Además, puedes mejorar tu implementación del conteo usando `getOrDefault()`.

Y algo importante para tus ejercicios: **no necesitas buscar siempre la solución más corta**. Primero intenta identificar correctamente la estructura de datos o algoritmo que resuelve el problema; después compara tu solución contra alternativas más eficientes. Esa práctica te va a enseñar mucho más que simplemente memorizar la solución con XOR.

*/