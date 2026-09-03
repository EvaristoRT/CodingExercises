import java.util.Map;
import java.util.Set;

public class PeteTheBaker {
    public static void main(String[] args) {
        /*
        Instrucción
        Pete likes to bake some cakes. He has some recipes and ingredients. Unfortunately he is not good in maths. Can you help him to find out, how many cakes he could bake considering his recipes?

        Write a function cakes(), which takes the recipe (object) and the available ingredients (also an object) and returns the maximum number of cakes Pete can bake (integer). For simplicity there are no units for the amounts (e.g. 1 lb of flour or 200 g of sugar are simply 1 or 200). Ingredients that are not present in the objects, can be considered as 0.

        Examples:

        // must return 2
        cakes({flour: 500, sugar: 200, eggs: 1}, {flour: 1200, sugar: 1200, eggs: 5, milk: 200}); 
        // must return 0
        cakes({apples: 3, flour: 300, sugar: 150, milk: 100, oil: 100}, {sugar: 500, flour: 2000, milk: 2000}); 
        */
    }
    public static int cakes(Map<String, Integer> recipe, Map<String, Integer> available) {
        Set<String> needs = recipe.keySet();
        int totalCakes = Integer.MAX_VALUE;
        for(String key : needs){
            if(available.containsKey(key) && available.get(key)>=recipe.get(key)){
                if (available.get(key)/recipe.get(key) < totalCakes) {
                    totalCakes = available.get(key)/recipe.get(key);
                }
            }else{
                return 0;
            }
        }
        return totalCakes;
    }
}
/*Retroalimentación
Sí, esta versión ya está **correcta** para el problema y además la lógica está bien planteada. Hay algunos puntos que se pueden mejorar, principalmente de **limpieza, eficiencia y legibilidad**.

### 1. La lógica principal está bien

Esta parte:

```java
int totalCakes = Integer.MAX_VALUE;
```

es una buena solución porque permite buscar el **mínimo** entre las cantidades posibles de cada ingrediente.

Por ejemplo:

```text
harina → 1200 / 500 = 2
azúcar → 1200 / 200 = 6
huevos → 5 / 1 = 5

mínimo → 2
```

Y tu condición:

```java
if (available.get(key)/recipe.get(key) < totalCakes)
```

va reduciendo `totalCakes` cuando encuentra un ingrediente que limita la cantidad de pasteles.

---

### 2. El `>=` está correctamente corregido

Esta modificación:

```java
available.get(key) >= recipe.get(key)
```

es correcta.

Aunque hay un detalle: **realmente no necesitas hacer esa comparación**.

Si hay menos ingredientes, la división entera ya produciría `0`.

Por ejemplo:

```java
200 / 500 = 0
```

Por lo tanto, esto:

```java
if(available.containsKey(key) && available.get(key)>=recipe.get(key)){
```

podría simplificarse a:

```java
if (available.containsKey(key)) {
```

porque después:

```java
available.get(key) / recipe.get(key)
```

ya determinará que se pueden hacer `0` pasteles.

---

### 3. Se puede evitar repetir los `get()`

Actualmente se hace varias veces:

```java
available.get(key)
recipe.get(key)
```

Por ejemplo:

```java
if (available.get(key)/recipe.get(key) < totalCakes) {
    totalCakes = available.get(key)/recipe.get(key);
}
```

Se puede hacer más legible:

```java
int possibleCakes = available.get(key) / recipe.get(key);

if (possibleCakes < totalCakes) {
    totalCakes = possibleCakes;
}
```

Esto además hace que el código sea más fácil de leer.

---

### 4. `Math.min()` lo hace todavía más limpio

La comparación:

```java
if (possibleCakes < totalCakes) {
    totalCakes = possibleCakes;
}
```

puede convertirse directamente en:

```java
totalCakes = Math.min(totalCakes, possibleCakes);
```

La solución quedaría:

```java
public static int cakes(Map<String, Integer> recipe, Map<String, Integer> available) {
    int totalCakes = Integer.MAX_VALUE;

    for (String key : recipe.keySet()) {
        if (!available.containsKey(key)) {
            return 0;
        }

        int possibleCakes = available.get(key) / recipe.get(key);
        totalCakes = Math.min(totalCakes, possibleCakes);
    }

    return totalCakes;
}
```

Esta sería mi versión preferida.

---

### 5. Incluso `Set<String> needs` es innecesario

Actualmente:

```java
Set<String> needs = recipe.keySet();

for(String key : needs)
```

No está mal, pero `keySet()` ya devuelve el conjunto de llaves. No es necesario crear una variable intermedia.

Puede ser directamente:

```java
for (String key : recipe.keySet())
```

Además, si se hace esto, ya no necesitarías:

```java
import java.util.Set;
```

---

### Evaluación general

**Lógica:** ⭐⭐⭐⭐⭐
**Correctitud:** ⭐⭐⭐⭐⭐
**Complejidad:** ⭐⭐⭐⭐⭐
**Legibilidad:** ⭐⭐⭐⭐☆
**Optimización:** ⭐⭐⭐⭐☆

La complejidad es **O(n)**, donde `n` es la cantidad de ingredientes de la receta, porque se recorre cada ingrediente una sola vez.

Lo más importante es que la solución ya refleja correctamente la idea matemática del ejercicio:

> **El ingrediente que permita producir la menor cantidad de pasteles determina el máximo número de pasteles que se pueden hacer.**

Así que no hay un problema algorítmico en tu solución. Las mejoras restantes son principalmente de **clean code** y de evitar operaciones/repeticiones innecesarias.

*/