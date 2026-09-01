import java.util.ArrayList;
import java.util.HashMap;

public class DeleteOccurrencesOfAnElementIfItOccursMoreThanNTimes {
    public static void main(String[] args) {
        /*Instrucción
        Enough is enough!
        Alice and Bob were on a holiday. Both of them took many pictures of the places they've been, and now they want to show Charlie their entire collection. However, Charlie doesn't like these sessions, since the motif usually repeats. He isn't fond of seeing the Eiffel tower 40 times.
        He tells them that he will only sit for the session if they show the same motif at most N times. Luckily, Alice and Bob are able to encode the motif as a number. Can you help them to remove numbers such that their list contains each number only up to N times, without changing the order?

        Task
        Given a list and a number, create a new list that contains each number of list at most N times, without reordering.
        For example if the input number is 2, and the input list is [1,2,3,1,2,1,2,3], you take [1,2,3,1,2], drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times, and then take 3, which leads to [1,2,3,1,2,3].
        With list [20,37,20,21] and number 1, the result would be [20,37,21].*/
    }
    public static int[] deleteNth(int[] elements, int maxOccurrences) {
		HashMap <Integer, Integer> words = new HashMap<>();
        ArrayList<Integer> finalElements = new ArrayList<>();
        for(int i = 0; i<elements.length; i++){
            if (words.containsKey(elements[i])) {
                if (words.get(elements[i])<maxOccurrences) {
                    finalElements.add(elements[i]);
                    words.replace(elements[i], words.get(elements[i])+1);
                }
            }else if (maxOccurrences !=0) {
                words.put(elements[i], 1);
                finalElements.add(elements[i]);
            }
        }
		return finalElements.stream().mapToInt(Integer::intValue).toArray();
	}
}
/*Retroalimentación
Sí. La solución está **bien planteada y es eficiente** para este ejercicio. De hecho, la elección de `HashMap` + `ArrayList` es bastante natural porque necesitas dos cosas simultáneamente:

* Saber **cuántas veces ha aparecido** cada número.
* Mantener el **orden original** de los elementos aceptados.

### 1. Complejidad

Tu solución tiene una complejidad aproximada de:

* **Tiempo:** `O(n)`
* **Espacio:** `O(n)`

Esto es prácticamente lo que se busca para este problema, porque cada elemento se procesa una sola vez y las operaciones de `HashMap` (`containsKey`, `get`, `put`, `replace`) son `O(1)` en promedio.

No tendría mucho sentido ordenar el array primero, por ejemplo, porque perderías el orden que el ejercicio exige conservar.

---

### 2. Lo que sí podría optimizarse: el `HashMap`

Actualmente tienes:

```java
if (words.containsKey(elements[i])) {
    if (words.get(elements[i]) < maxOccurrences) {
        finalElements.add(elements[i]);
        words.replace(elements[i], words.get(elements[i]) + 1);
    }
}
```

Aquí estás haciendo varias búsquedas en el `HashMap` para el mismo elemento:

```java
containsKey()
get()
replace()
```

Se puede simplificar utilizando `getOrDefault()`:

```java
int occurrences = words.getOrDefault(elements[i], 0);

if (occurrences < maxOccurrences) {
    finalElements.add(elements[i]);
    words.put(elements[i], occurrences + 1);
}
```

Esto resulta más limpio porque conceptualmente haces:

> "Dame cuántas veces apareció este número; si nunca apareció, dame 0."

---

### 3. Incluso puede hacerse más compacto

La función podría quedar:

```java
public static int[] deleteNth(int[] elements, int maxOccurrences) {
    HashMap<Integer, Integer> occurrences = new HashMap<>();
    ArrayList<Integer> finalElements = new ArrayList<>();

    for (int element : elements) {
        int count = occurrences.getOrDefault(element, 0);

        if (count < maxOccurrences) {
            finalElements.add(element);
            occurrences.put(element, count + 1);
        }
    }

    return finalElements.stream()
            .mapToInt(Integer::intValue)
            .toArray();
}
```

Hay dos cambios interesantes aquí.

### `for-each`

En lugar de:

```java
for(int i = 0; i < elements.length; i++){
    elements[i]
}
```

puedes hacer:

```java
for (int element : elements) {
```

Como **no necesitas conocer el índice**, el `for-each` expresa mejor la intención:

> "Por cada elemento del array..."

---

### `getOrDefault()`

Esto:

```java
int count = occurrences.getOrDefault(element, 0);
```

reemplaza la necesidad de hacer:

```java
if (occurrences.containsKey(element)) {
    ...
} else {
    ...
}
```

Por ejemplo, si el `HashMap` contiene:

```text
1 → 2
3 → 1
7 → 4
```

y estás procesando un `5`:

```java
occurrences.getOrDefault(5, 0)
```

devuelve:

```text
0
```

porque `5` todavía no existe.

---

## 4. Hay algo que hiciste particularmente bien

Esta parte:

```java
else if (maxOccurrences != 0)
```

está resolviendo correctamente el caso de:

```java
maxOccurrences = 0
```

Aunque con la versión usando `getOrDefault()` ya ni siquiera necesitas ese caso especial:

```java
if (count < maxOccurrences)
```

Si `maxOccurrences` es `0`:

```text
count < 0
```

será siempre falso, por lo que simplemente no se agrega ningún elemento.

---

## 5. ¿Se podría evitar el `ArrayList`?

Sí, pero **no necesariamente sería mejor**.

Como el resultado puede tener un tamaño desconocido, `ArrayList` es una buena elección.

Podría hacerse primero un array de tamaño `elements.length` y después copiar solamente la parte utilizada, pero sería más código y probablemente menos claro:

```text
int[] resultado = new int[elements.length];
int index = 0;
...
return Arrays.copyOf(resultado, index);
```

Tu decisión de utilizar:

```java
ArrayList<Integer> finalElements
```

es bastante adecuada.

---

### Evaluación general

Diría que la solución está **bien estructurada y con una buena elección de estructuras de datos**. Lo principal que mejoraría no es la estrategia, sino la forma de implementarla:

```text
HashMap<Integer, Integer>
        ↓
contar ocurrencias

ArrayList<Integer>
        ↓
mantener elementos válidos en orden

Stream
        ↓
convertir ArrayList<Integer> → int[]
```

La mejora más importante sería familiarizarse con `getOrDefault()` y `for-each`, porque ambos hacen que el código sea más idiomático en Java.

Y **no cambiaría la estrategia general por una supuesta solución "más óptima"**: para este problema, `HashMap + ArrayList` con una pasada sobre el array ya es una solución muy buena.

*/