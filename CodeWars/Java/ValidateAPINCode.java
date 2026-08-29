public class ValidateAPINCode {
    public static void main(String[] args) {
        /*Instrucción
            ATM machines allow 4 or 6 digit PIN codes and PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits.
            If the function is passed a valid PIN string, return true, else return false.
            Examples (Input --> Output)
            "1234"   -->  true
            "12345"  -->  false
            "a234"   -->  false
        */
    }
    public static boolean validatePin(String pin) {
        if (!pin.matches("\\d+") || (pin.length() != 4 && pin.length()!=6)) {
            return false;
        }
        return true;
    }
}
/*Retroalimentación
La solución es **correcta, sencilla y suficientemente eficiente** para este ejercicio. La lógica implementada cumple exactamente con las condiciones solicitadas.

### 1. La condición está bien planteada

if (!pin.matches("\\d+") || (pin.length() != 4 && pin.length()!=6)) {
    return false;
}

Se están comprobando dos cosas:

1. Que todos los caracteres sean dígitos:

    !pin.matches("\\d+")

2. Que la longitud sea diferente de 4 **y** diferente de 6:

    pin.length() != 4 && pin.length() != 6

Al utilizar `||`, basta con que una de las dos condiciones sea verdadera para determinar que el PIN no es válido.

---

### 2. El `return true` podría simplificarse

Actualmente:

if (...) {
    return false;
}

return true;

Como la condición ya representa exactamente cuándo el PIN es inválido, puede invertirse y devolver directamente el resultado:

return pin.matches("\\d+") &&
        (pin.length() == 4 || pin.length() == 6);

Esta versión expresa directamente la definición de un PIN válido:

> Es válido si contiene solamente dígitos **y** tiene 4 o 6 caracteres.

No es necesariamente "más eficiente" de manera significativa; principalmente es **más concisa y declarativa**.

---

### 3. El Regex podría ser todavía más específico

Actualmente se utiliza:

```java
"\\d+"
```

Esto significa:

> uno o más dígitos.

Pero el problema establece que solamente pueden existir **4 o 6 dígitos**.

Una alternativa sería expresar prácticamente toda la validación mediante Regex:

"\\d{4}|\\d{6}"

Donde:

\d{4} → exactamente 4 dígitos
\d{6} → exactamente 6 dígitos
|     → OR

Por lo tanto, se podría tener:

return pin.matches("\\d{4}|\\d{6}");

Esto elimina completamente la necesidad de `length()`.

Sin embargo, **la solución original tiene una ventaja didáctica**: separa claramente las dos reglas del problema:

¿Son todos dígitos?
¿Tiene 4 o 6 caracteres?

### 4. Una pequeña consideración sobre `matches()`

En este caso:

pin.matches("\\d+")

es adecuado porque `String.matches()` intenta hacer coincidir **todo el String**, no simplemente encontrar un fragmento.

Por ejemplo:

"1234" → true
"a234" → false
"1234a" → false
"12 34" → false

### 5. Complejidad

La solución tiene una complejidad temporal de aproximadamente:

**O(n)**

porque Regex necesita revisar los caracteres del String.

El espacio adicional es aproximadamente:

**O(1)**

para esta operación.

Para un PIN de únicamente 4 o 6 caracteres, la diferencia de rendimiento entre las distintas soluciones es prácticamente irrelevante. La elección debería basarse principalmente en **claridad y mantenibilidad**.

En general, es una solución bastante limpia. La principal mejora sería convertir la condición en un `return` directo o explorar la alternativa de expresar **todas las reglas mediante Regex**.
 */