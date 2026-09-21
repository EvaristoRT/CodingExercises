public class TwoOldestAges {
    public static void main(String[] args) {
        /*Instrucción
        The two oldest ages function/method needs to be completed. It should take an array of numbers as its argument and return the two highest numbers within the array. The returned value should be an array in the format [second oldest age,  oldest age].

        The order of the numbers passed in could be any order. The array will always include at least 2 items. If there are two or more oldest age, then return both of them in array format.

        For example (Input --> Output):

        [1, 2, 10, 8] --> [8, 10]
        [1, 5, 87, 45, 8, 8] --> [45, 87]
        [1, 3, 10, 0]) --> [3, 10]
        */
    }

    public static int[] twoOldestAges(int[] ages) {
        int age1 = 0;
        int age2 = 0;

        for(int edad : ages){
            if (edad > age1 && edad < age2) {
                age1 = edad;
            } else if (edad >= age2) {
                age1 = age2;
                age2 = edad;
            }
        }

        return new int[]{age1,age2}; // your code here
    }
}
/*Retroalimentación
Sí, tu solución ya está **mucho más cerca** y, para los casos normales del kata, la idea que estás usando es correcta. Vamos por partes.

### 1. La estructura general está bien

Tienes dos variables:

```java
int age1 = 0;
int age2 = 0;
```

y estás intentando mantener:

* `age1` → segundo número más grande
* `age2` → número más grande

Eso es una buena estrategia porque permite resolverlo en **una sola pasada** por el arreglo.

Además, el retorno:

```java
return new int[]{age1, age2};
```

está perfectamente hecho y respeta el formato solicitado `[second oldest, oldest]`.

---

### 2. El `else if` está bien planteado

Esta parte:

```java
else if (edad >= age2) {
    age1 = age2;
    age2 = edad;
}
```

es importante que uses `>=` y no solamente `>`.

Por ejemplo:

```text
[1, 5, 87, 45, 8, 8]
```

Cuando llegas a `87`:

```text
age1 = 5
age2 = 87
```

Después `45`:

```text
age1 = 45
age2 = 87
```

Y los `8` no modifican nada.

El resultado termina siendo:

```text
[45, 87]
```

correctamente.

---

### 3. Tu primera condición es la parte que revisaría

Tienes:

```java
if (edad > age1 && edad < age2) {
    age1 = edad;
}
```

La idea es buena: si encontramos un número que está **entre el segundo mayor y el mayor**, entonces debe convertirse en el nuevo segundo mayor.

Por ejemplo, si tenemos:

```text
age1 = 45
age2 = 87
```

y aparece:

```text
60
```

entonces:

```text
60 > 45 && 60 < 87
```

es `true`, por lo que:

```text
age1 = 60
```

Perfecto.

---

### 4. Hay un detalle importante con los `0`

Tu inicialización:

```java
int age1 = 0;
int age2 = 0;
```

funciona si las edades son positivas, que es lo razonable para este kata.

Pero conceptualmente estás haciendo que `0` sea tu valor inicial. Si el arreglo pudiera contener números negativos, tu algoritmo tendría problemas.

Por ejemplo:

```java
[-5, -2, -10]
```

terminaría sin representar correctamente los dos valores mayores.

Para **este kata**, esto probablemente no te afecta porque estamos hablando de edades, pero desde el punto de vista de programación, es una dependencia de una suposición sobre los datos.

---

### 5. Complejidad

Aquí tienes algo bastante bueno:

**Tiempo: `O(n)`**

Solo recorres el arreglo una vez:

```java
for(int edad : ages)
```

**Espacio adicional: `O(1)`**

Solo utilizas:

```java
age1
age2
```

sin crear estructuras auxiliares.

Por lo tanto, tu enfoque es **óptimo en complejidad** para este problema.

---

### 6. Un pequeño detalle de estilo

Yo cambiaría:

```java
for(int edad : ages){
```

por:

```java
for (int edad : ages) {
```

y:

```java
return new int[]{age1,age2};
```

por:

```java
return new int[]{age1, age2};
```

Es solamente formato, pero hace que el código sea más consistente y legible.

---

### Evaluación

| Aspecto                         | Evaluación   |
| ------------------------------- | ------------ |
| Idea/algoritmo                  | ✅ Muy buena  |
| Complejidad                     | ✅ Óptima     |
| Manejo de duplicados            | ✅ Correcto   |
| Orden del resultado             | ✅ Correcto   |
| Retorno del arreglo             | ✅ Correcto   |
| Legibilidad                     | 🟢 Buena     |
| Inicialización                  | 🟡 Mejorable |
| Dependencia de edades positivas | 🟡 Existe    |

**En general: buen ejercicio.** La parte más importante es que llegaste a una solución de una pasada y sin ordenar el arreglo. Eso es mejor que simplemente hacer `Arrays.sort()` y tomar los dos últimos elementos, porque tu solución mantiene `O(n)` en lugar de `O(n log n)`.

Y algo interesante: **no necesitas ordenar el arreglo ni usar `ArrayList`, `HashSet`, etc.** Tus dos variables son suficientes.

*/