import java.util.HashSet;

public class EstimatingAmountsOfSubsets {
    public static void main(String[] args) {
        /*Instrucción
        Given a set of elements (integers or string characters, characters only in RISC-V), where any element may occur more than once, return the number of nonempty subsets that do not contain a repeated element.

        Let's see with an example:

        set numbers = {1, 2, 3, 4}
        The subsets are:

        {{1}, {2}, {3}, {4}, {1,2}, {1,3}, {1,4}, {2,3}, {2,4}, 
        {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {2,3,4}, {1,2,3,4}}
        There are 15 subsets. As you can see, the empty set, {}, is not counted.

        Let's see an example with repetitions of an element:

        set letters = {a, b, c, d, d}
        The subsets for this case (including only those that have no repeated elements inside) will be:

        {{a}, {b}, {c}, {d}, {a,b}, {a,c}, {a,d}, {b,c}, {b,d},
        {c,d}, {a,b,c}, {a,b,d}, {a,c,d}, {b,c,d}, {a,b,c,d}}
        There are 15 subsets.

        The function should receive an array as an argument and according to its features output the amount of nonempty subsets that do not contain a repeated element.

        [1, 2, 3, 4] --> 15
        ['a', 'b', 'c', 'd', 'd'] --> 15
        Features of the random tests:

        Low Performance Tests: 40
        Length of the arrays between 6 and 15

        High Performance Tests: 80
        Length of the arrays between 15 and 100 (Python and Ruby) between 15 and 63 (C++) and between 15 and 50 in javascript and Lua
        Just do it!
        */
    }

    public static <T> long count(T[] elems) {
        HashSet <T> setElements = new HashSet<>();
        for(T element : elems){
            setElements.add(element);
        }
        return (long)Math.pow(2, setElements.size())-1;
    }  
}
/*Retroalimentación
Ahora sí, tu solución está **correcta** para lo que pide el ejercicio. 👍

```java
public static <T> long count(T[] elems) {
    HashSet<T> setElements = new HashSet<>();

    for (T element : elems) {
        setElements.add(element);
    }

    return (long) Math.pow(2, setElements.size()) - 1;
}
```

### 1. La lógica es correcta

Tu solución hace exactamente las dos cosas importantes:

**Primero, eliminas duplicados:**

```java
HashSet<T> setElements = new HashSet<>();
```

y:

```java
for (T element : elems) {
    setElements.add(element);
}
```

Por ejemplo:

```text
[a, b, c, d, d]
```

termina como:

```text
[a, b, c, d]
```

Por lo tanto:

```java
setElements.size() // 4
```

Después aplicas:

$$
2^4-1=15
$$

Correcto.

---

### 2. El `<T>` está muy bien utilizado

Esta parte:

```java
public static <T> long count(T[] elems)
```

permite que tu método funcione tanto con:

```java
Integer[]
```

como con:

```java
String[]
```

Por ejemplo:

```java
count(new Integer[]{1, 2, 3, 4});
```

y:

```java
count(new String[]{"a", "b", "c", "d", "d"});
```

sin tener que crear dos métodos diferentes.

---

### 3. Complejidad

Aquí está uno de los puntos fuertes de tu solución.

Tu código tiene:

**Tiempo:**

$$
O(n)
$$

porque recorres el arreglo una vez y las operaciones normales de `HashSet` son, en promedio, \(O(1)\).

**Espacio:**

$$
O(n)
$$

porque en el peor caso todos los elementos son diferentes y el `HashSet` almacena los `n` elementos.

Esto es **muchísimo mejor** que intentar generar físicamente todas las combinaciones.

El número de subconjuntos crece como:

$$
2^n-1
$$

pero tú **no necesitas generarlos**. Solamente calculas cuántos existen.

---

### 4. Un detalle que mejoraría

Esta parte:

```java
(long)Math.pow(2, setElements.size()) - 1
```

funciona para los límites de este ejercicio, pero hay una alternativa más apropiada para potencias de 2.

`Math.pow()` devuelve `double`, por lo que estás haciendo:

```text
int → double → long
```

Puedes hacerlo directamente con operaciones enteras:

```java
(1L << setElements.size()) - 1
```

Por ejemplo, si hay 4 elementos únicos:

```java
(1L << 4) - 1
```

equivale a:

```text
10000₂ - 1
01111₂
```

= **15**

Además, la `L` hace que el `1` sea un `long`.

**Peeero:** no cambiaría tu solución solamente por cambiarla. Para este ejercicio, `Math.pow()` es perfectamente entendible y tu solución pasa conceptualmente la prueba.

---

### 5. Algo importante sobre el `HashSet`

Tu solución demuestra que entendiste la parte clave del problema:

> **Los duplicados del arreglo no deben aumentar el número de elementos disponibles para formar un subconjunto.**

Por eso:

```text
[a, b, c, d, d]
```

se comporta como si fuera:

```text
[a, b, c, d]
```

y no como 5 elementos.

---

### Mi valoración

| Aspecto          | Evaluación          |
| ---------------- | ------------------- |
| Lógica           | ✅ Correcta          |
| Uso de `HashSet` | ✅ Muy adecuado      |
| Genéricos `<T>`  | ✅ Correcto          |
| Fórmula          | ✅ Correcta          |
| Complejidad      | ✅ O(n)              |
| Memoria          | ✅ O(n)              |
| Legibilidad      | ✅ Buena             |
| Optimización     | 🟢 Buena            |
| `Math.pow()`     | 🟡 Se puede mejorar |

**Para tu nivel actual de Java, es una muy buena solución.** Sobre todo porque llegaste a la idea de **eliminar duplicados → contar únicos → aplicar \(2^n-1\)** en lugar de intentar construir todos los subconjuntos.

*/