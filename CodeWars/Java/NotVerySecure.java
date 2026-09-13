public class NotVerySecure {
    public static void main(String[] args) {
        /*Instrucción
        In this example you have to validate if a user input string is alphanumeric. The given string is not nil/null/NULL/None, so you don't have to check that.

        The string has the following conditions to be alphanumeric:

        At least one character ("" is not valid)
        Allowed characters are uppercase / lowercase latin letters and digits from 0 to 9
        No whitespaces / underscore
        */
    }

    public static boolean alphanumeric(String s){
        return s.matches("^[a-zA-Z0-9]+$");
    }
}
/*Retroalimentación
Tu solución está **muy bien** y, para este kata, es prácticamente la solución ideal en cuanto a simplicidad. Vamos a revisarla por partes.

### 1. La lógica

```java
return s.matches("^[a-zA-Z0-9]+$");
```

Es correcta para todas las condiciones del ejercicio:

* `[a-zA-Z]` → acepta letras latinas mayúsculas y minúsculas.
* `[0-9]` → acepta dígitos del 0 al 9.
* `+` → exige **al menos un carácter**, por lo que `""` devuelve `false`.
* `^` → inicio de la cadena.
* `$` → final de la cadena.
* Al no incluir espacios, `_`, `ñ`, acentos, símbolos, etc., estos son rechazados.

Por ejemplo:

| Entrada         | Resultado |
| --------------- | --------: |
| `"abc123"`      |    `true` |
| `"ABC123"`      |    `true` |
| `"123456"`      |    `true` |
| `"hello"`       |    `true` |
| `"hello world"` |   `false` |
| `"hello_world"` |   `false` |
| `"hello!"`      |   `false` |
| `"ábc123"`      |   `false` |
| `""`            |   `false` |

### 2. ¿Es la solución más óptima?

Para este kata, **sí es una de las mejores soluciones**.

`String.matches()` permite expresar directamente la regla del problema mediante una expresión regular, sin necesidad de:

* recorrer el `String` manualmente;
* crear arreglos;
* utilizar `Character.isLetterOrDigit()`;
* utilizar `HashSet`;
* hacer múltiples condiciones `if`.

Tu solución tiene una complejidad aproximada de **O(n)**, porque la expresión regular debe revisar los caracteres de la cadena.

En memoria, es esencialmente **O(1)** adicional desde el punto de vista conceptual, aunque internamente `matches()` utiliza mecanismos de regex.

### 3. Una pequeña mejora de la expresión

Podrías incluso eliminar `^` y `$`:

```java
return s.matches("[a-zA-Z0-9]+");
```

En Java, `String.matches()` intenta hacer coincidir **toda la región de la cadena**, por lo que los anclajes no son necesarios aquí.

Así que:

```java
s.matches("^[a-zA-Z0-9]+$")
```

y

```java
s.matches("[a-zA-Z0-9]+")
```

te sirven para este caso.

Personalmente, **me quedaría con tu versión** mientras estás aprendiendo regex porque `^` y `$` hacen explícito visualmente que quieres validar toda la cadena.

### 4. Una alternativa sin regex

También podrías hacerlo recorriendo cada carácter:

```java
public static boolean alphanumeric(String s) {
    for (char c : s.toCharArray()) {
        if (!((c >= 'a' && c <= 'z') ||
              (c >= 'A' && c <= 'Z') ||
              (c >= '0' && c <= '9'))) {
            return false;
        }
    }

    return !s.isEmpty();
}
```

Pero para este problema **no considero que sea mejor que la tuya**. Es bastante más código para expresar exactamente la misma regla.

Incluso `Character.isLetterOrDigit()` no sería apropiado si se usa directamente:

```java
Character.isLetterOrDigit(c)
```

porque acepta letras de otros alfabetos, mientras que el kata pide específicamente **letras latinas**.

### 5. Algo interesante que hiciste bien

Hay una pequeña trampa en este kata: utilizar algo como:

```java
return s.matches("[a-zA-Z0-9]*");
```

sería incorrecto, porque `*` permite **cero o más** caracteres.

Entonces:

```java
"".matches("[a-zA-Z0-9]*")
```

daría `true`.

Tú utilizaste:

```regex
+
```

que significa **uno o más**, exactamente lo que pide el ejercicio.

### Veredicto

**Tu solución: 9.5/10.**

La lógica está correcta, es corta, legible y apropiada para el problema. No veo una optimización necesaria que haga que valga la pena reemplazarla.

Lo más importante que te llevas de este kata es precisamente la diferencia entre **`*` y `+` en regex**, y que `Character.isLetterOrDigit()` no necesariamente significa "letra latina o número ASCII".

*/