public class GiveMeADiamond {
    public static void main(String[] args) {
        /*Instrucción
        Jamie is a programmer, and James' girlfriend. She likes diamonds, and wants a diamond string from James. Since James doesn't know how to make this happen, he needs your help.

        Task
        You need to return a string that looks like a diamond shape when printed on the screen, using asterisk (*) characters. Trailing spaces should be removed, and every line must be terminated with a newline character (\n).

        Return null/nil/None/... if the input is an even number or negative, as it is not possible to print a diamond of even or negative size.

        Examples
        A size 3 diamond:

         *
        ***
         *
        ...which would appear as a string of " *\n***\n *\n"

        A size 5 diamond:

          *
         ***
        *****
         ***
          *
        ...that is:

        "  *\n ***\n*****\n ***\n  *\n"

                */
    }

    public static String print(int n) {
        if (n % 2 == 0 || n <= 0) {
            return null;
        }
        StringBuilder finalString = new StringBuilder();
        int maxSpaces = ((n + 1) / 2) - 1;
        int cantidadEspacios = maxSpaces;
        for (int i = 1; i <= n; i += 2) {
            for (int j = 1; j <= cantidadEspacios;j++){
                finalString.append(" ");
            }

            for(int j = 1; j <= i; j++){
                finalString.append("*");
            }

            finalString.append("\n");
            cantidadEspacios--;
        }
        cantidadEspacios +=2;
        for (int i = n - 2; i >= 1; i -= 2) {

            for(int j = 1; j<= cantidadEspacios; j++){
                finalString.append(" ");
            }

            for(int j = 1; j<= i; j++){
                finalString.append("*");
            }

            finalString.append("\n");
            cantidadEspacios++;
        }

        return finalString.toString();
    }
}
/*Retroalimentación
Sí, ahora sí tienes una solución **correcta y bastante sólida**. Para un kata de Codewars, tu enfoque es perfectamente válido. Te doy la retroalimentación separando **correctitud, complejidad, claridad y posibles mejoras**.

### 1. Correctitud: ✅

Tu validación está bien:

```java
if (n % 2 == 0 || n <= 0) {
    return null;
}
```

Cubre:

* `n` par → `null`
* `n = 0` → `null`
* `n` negativo → `null`
* `n` impar positivo → continúa

También manejas correctamente el requisito de que **cada línea termine en `\n`**:

```java
finalString.append("\n");
```

Y no agregas espacios después de los `*`, por lo que tampoco generas trailing spaces.

---

### 2. La división en dos partes está muy bien

Esta es probablemente la parte más importante de tu solución:

```java
for (int i = 1; i <= n; i += 2)
```

para construir:

```text
*
***
*****
```

y después:

```java
for (int i = n - 2; i >= 1; i -= 2)
```

para construir:

```text
***
*
```

Esto es bastante intuitivo.

Además, usar:

```java
n - 2
```

es correcto porque el centro ya fue generado en la primera mitad. Si pusieras `n` otra vez, repetirías:

```text
*****
```

---

### 3. `StringBuilder`: ✅ Muy buena elección

Esto:

```java
StringBuilder finalString = new StringBuilder();
```

es mejor que hacer concatenaciones repetidas como:

```java
String resultado = "";
resultado += "*";
```

Porque `String` es inmutable y las concatenaciones repetidas pueden generar muchos objetos intermedios.

Para este ejercicio, `StringBuilder` es la elección natural.

---

### 4. La lógica de los espacios funciona, pero es la parte menos limpia

Tienes:

```java
int maxSpaces = ((n + 1) / 2) - 1;
int cantidadEspacios = maxSpaces;
```

y después:

```java
cantidadEspacios--;
```

Hasta ahí está muy bien.

El punto que me parece menos intuitivo es:

```java
cantidadEspacios += 2;
```

¿Por qué `+2`?

Porque al terminar el primer `for`, `cantidadEspacios` queda en `-1`.

Por ejemplo, con `n = 5`:

```text
2
1
0
-1   ← después de la última disminución
```

Entonces:

```java
cantidadEspacios += 2;
```

lo convierte en:

```text
1
```

y puedes comenzar la segunda mitad.

**Funciona perfectamente**, pero cuando otra persona lea tu código puede preguntarse:

> "¿Por qué demonios se suman 2 espacios aquí?"

Eso afecta un poco la legibilidad.

Una alternativa más explícita sería:

```java
cantidadEspacios = 1;
```

en ese punto.

No es necesariamente más "óptima", pero sí expresa mejor la intención:

> "La primera fila de la mitad inferior tiene un espacio."

---

### 5. Podrías mejorar los nombres

Actualmente tienes:

```java
int maxSpaces
int cantidadEspacios
```

Estás mezclando inglés y español.

No afecta absolutamente nada al funcionamiento, pero como estás practicando Java, yo intentaría mantener un solo idioma.

Por ejemplo:

```java
int maxSpaces;
int currentSpaces;
```

o todo en español:

```java
int espaciosMaximos;
int espaciosActuales;
```

Personalmente, en código profesional te recomendaría acostumbrarte al inglés:

```java
int maxSpaces;
int currentSpaces;
StringBuilder diamond;
```

---

### 6. Los `for` internos también están correctos

Tienes:

```java
for (int j = 1; j <= cantidadEspacios; j++) {
    finalString.append(" ");
}
```

y:

```java
for (int j = 1; j <= i; j++) {
    finalString.append("*");
}
```

Funcionan perfectamente.

Una pequeña mejora de estilo sería:

```java
finalString.append(" ");
```

y:

```java
finalString.append("*");
```

están bien, aunque `append('*')` también es posible:

```java
finalString.append('*');
```

Para un único carácter, `append(char)` expresa mejor que estás agregando un carácter y no una cadena.

**Pero esto es una mejora mínima**, no algo que haga tu algoritmo más eficiente de manera relevante.

---

## 7. Complejidad

Tu solución es aproximadamente:

**Tiempo: O(n²)**
**Espacio: O(n²)**

La razón del espacio es que estás construyendo un `String` que puede contener aproximadamente \(n^2\) caracteres.

Y esto es razonable porque **el resultado que tienes que devolver ya tiene ese tamaño**. No puedes devolver un diamante de tamaño `n` sin almacenar sus caracteres en algún momento.

Así que no me preocuparía por intentar llevar esto a O(n) solamente por "optimización". Para este problema, tu complejidad es adecuada.

---

## 8. ¿Qué tan buena es tu solución?

Yo la evaluaría aproximadamente así:

| Aspecto                   | Evaluación |
| ------------------------- | ---------- |
| Correctitud               | ⭐⭐⭐⭐⭐      |
| Manejo de casos inválidos | ⭐⭐⭐⭐⭐      |
| Uso de `StringBuilder`    | ⭐⭐⭐⭐⭐      |
| Complejidad               | ⭐⭐⭐⭐½      |
| Legibilidad               | ⭐⭐⭐⭐       |
| Simplicidad               | ⭐⭐⭐⭐       |
| Uso de Java               | ⭐⭐⭐⭐⭐      |

**Lo más importante:** no estás haciendo algo innecesariamente complicado. La solución se basa directamente en la estructura matemática del diamante:

```text
1, 3, 5, ..., n
n-2, n-4, ..., 1
```

y controlas los espacios por separado.

### Mi principal recomendación

No cambiaría tu algoritmo. Simplemente limpiaría esa transición:

```java
cantidadEspacios += 2;
```

por algo más expresivo como:

```java
cantidadEspacios = 1;
```

y mantendría el resto de tu enfoque.

Para un ejercicio de nivel Codewars, **ya es una solución bastante buena**. De hecho, lo interesante de este ejercicio es que llegaste a una solución funcional manejando tú mismo la relación entre `*` y espacios, en lugar de intentar buscar una fórmula enorme para todo el diamante.

*/