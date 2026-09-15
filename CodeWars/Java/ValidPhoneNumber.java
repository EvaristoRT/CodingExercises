public class ValidPhoneNumber {
    public static void main(String[] args) {
        /*Instrucción
        Write a function that accepts a string, and returns true if it is in the form of a phone number.
        Assume that any integer from 0-9 in any of the spots will produce a valid phone number.

        Only worry about the following format:
        (123) 456-7890 (don't forget the space after the close parentheses)

        Examples:

        "(123) 456-7890"  => true
        "(1111)555 2345"  => false
        "(098) 123 4567"  => false
        */
    }

    public static boolean validPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$")) {
            return true;
        }
        return false;
    }
}
/*Retroalimentación
Tu solución está **correcta y bastante óptima** para este kata. De hecho, la parte importante —el regex— está bien planteada.

### 1. Regex: ✅ Correcto

```java
"^\\(\\d{3}\\) \\d{3}-\\d{4}$"
```

Valida exactamente:

```text
(123) 456-7890
```

Y rechaza correctamente casos como:

```text
(1111)555 2345  ❌
(098) 123 4567  ❌
(123)456-7890   ❌
(123) 4567-890  ❌
```

El uso de:

```java
\\(
\\)
```

es necesario para tratar los paréntesis como caracteres literales.

Y la estructura:

```text
(123) 456-7890
 │ │   │   │
 │ │   │   └── 4 dígitos
 │ │   └────── guion
 │ └────────── 3 dígitos
 └──────────── 3 dígitos entre paréntesis
```

está perfectamente reflejada.

---

### 2. `matches()` + `^` y `$`

Aquí hay un pequeño detalle interesante.

Tienes:

```java
phoneNumber.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$")
```

En Java, `String.matches()` intenta hacer coincidir **toda la cadena**, por lo que `^` y `$` realmente son innecesarios.

Podrías dejarlo simplemente como:

```java
phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}")
```

Pero **tu versión no está mal**. De hecho, `^` y `$` hacen explícita tu intención de que el patrón abarque desde el principio hasta el final.

---

### 3. El `if` puede simplificarse

Actualmente haces:

```java
if (phoneNumber.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$")) {
    return true;
}
return false;
```

Esto funciona perfectamente, pero estás haciendo una operación que ya devuelve `boolean` para después volver a convertirla en `true` o `false`.

Puedes retornar directamente:

```java
return phoneNumber.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$");
```

Esto sería más limpio y más idiomático en Java.

---

### 4. Evaluación general

| Aspecto             | Evaluación              |
| ------------------- | ----------------------- |
| Lógica              | ✅ Correcta              |
| Regex               | ✅ Correcto              |
| Casos del ejercicio | ✅ Los cubre             |
| Complejidad         | ✅ Muy buena             |
| Legibilidad         | ✅ Buena                 |
| Uso de `matches()`  | ✅ Apropiado             |
| `if` innecesario    | ⚠️ Se puede simplificar |

**Complejidad:** `O(n)`, donde `n` es la longitud de la cadena. En este kata, como el formato siempre tiene una longitud fija de 14 caracteres, en la práctica es prácticamente constante.

### Mi versión final

```java
public static boolean validPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$");
}
```

**Conclusión:** tu solución original ya es **correcta y eficiente**. El único cambio que realmente recomendaría es eliminar el `if`, porque `matches()` ya te entrega exactamente el `boolean` que necesitas.

*/