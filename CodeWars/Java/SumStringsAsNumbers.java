public class SumStringsAsNumbers {
    public static void main(String[] args) {
        /*Instrucción
        Given the string representations of two integers, return the string representation of the sum of those integers.

        For example:

        sumStrings('1','2') // => '3'
        A string representation of an integer will contain no characters besides the ten numerals "0" to "9".

        I have removed the use of BigInteger and BigDecimal in java

        Python: your solution need to work with huge numbers (about a milion digits), converting to int will not work.
        */
    }
    public static String sumStrings(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {

            int digitA = i >= 0 ? a.charAt(i) - '0' : 0;
            int digitB = j >= 0 ? b.charAt(j) - '0' : 0;

            int sum = digitA + digitB + carry;

            result.append(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }

        String resultString = result.reverse().toString();

        return resultString.replaceFirst("^0+(?!$)", "");
    }
}
/*Retroalimentación
Claro. Viendo todo el proceso, este ejercicio estuvo bastante bien porque **la solución fue evolucionando a partir de los errores reales de los tests**, en lugar de simplemente copiar una solución final.

## 1. Tu primera solución

Comenzaste con:

```java
public static String sumStrings(String a, String b) {
    return String.valueOf(Integer.valueOf(a) + Integer.valueOf(b));
}
```

### Lo bueno

La lógica era completamente válida para números pequeños:

```text
"123" → 123
"456" → 456

123 + 456 → 579

579 → "579"
```

Es una solución **simple, legible y directa**.

El problema no era que no supieras sumar los números. El problema era que no tomaste en cuenta una restricción importante del Kata: los números pueden tener **cientos de miles o incluso un millón de dígitos**.

Por lo tanto:

```java
Integer.valueOf(a)
```

es el verdadero problema.

---

# 2. Identificaste la restricción importante

Al revisar la instrucción, aparece:

> "about a milion digits"

Eso cambia completamente el enfoque.

Si tenemos:

```text
"999999999999999999999999999999999999999999..."
```

no podemos hacer:

```java
Integer.valueOf(...)
```

ni siquiera:

```java
Long.valueOf(...)
```

Y el ejercicio además prohíbe apoyarse en `BigInteger`/`BigDecimal`.

Por lo tanto, la conclusión fue:

> **El número no se debe convertir a un tipo numérico; se debe trabajar directamente con los caracteres del String.**

Ese fue el cambio conceptual más importante del ejercicio.

---

# 3. Llegaste a la suma manual

La siguiente idea fue pensar:

> "¿Cómo haría esto si tuviera que sumar los números a mano?"

Por ejemplo:

```text
   999
 + 123
 -----
  1122
```

Se comienza desde la derecha:

```text
9 + 3 = 12
```

Se coloca:

```text
2
```

y se lleva:

```text
1
```

Después:

```text
9 + 2 + 1 = 12
```

Y nuevamente:

```text
2
```

con `carry = 1`.

Eso llevó naturalmente a estas variables:

```java
int i = a.length() - 1;
int j = b.length() - 1;
int carry = 0;
```

Y ahí ya tienes las piezas fundamentales del algoritmo.

---

# 4. El uso de `charAt() - '0'`

Esta parte también fue importante:

```java
int digitA = a.charAt(i) - '0';
```

Porque:

```java
a.charAt(i)
```

nos da un carácter:

```text
'9'
```

pero necesitamos:

```text
9
```

La resta:

```java
'9' - '0'
```

produce:

```text
9
```

Así se puede trabajar con cada dígito sin convertir el número completo.

---

# 5. El `carry`

Después llegamos a:

```java
int sum = digitA + digitB + carry;
```

Y:

```java
result.append(sum % 10);
carry = sum / 10;
```

Esta es probablemente la parte más importante del algoritmo.

Si tenemos:

```text
9 + 3 = 12
```

entonces:

```java
sum % 10
```

nos da:

```text
2
```

y:

```java
sum / 10
```

nos da:

```text
1
```

Así se simula exactamente la suma que haría una persona.

---

# 6. El problema de números con diferentes longitudes

También hubo que considerar casos como:

```text
  123
+   9
-----
  132
```

Por eso utilizamos:

```java
while (i >= 0 || j >= 0 || carry > 0)
```

Y esta parte:

```java
int digitA = i >= 0 ? a.charAt(i) - '0' : 0;
int digitB = j >= 0 ? b.charAt(j) - '0' : 0;
```

permite tratar un número que ya se terminó como `0`.

Por ejemplo:

```text
  123
+   9
```

cuando llegamos a los últimos dígitos:

```text
a → todavía tiene dígitos
b → ya no tiene
```

entonces:

```java
digitB = 0;
```

---

# 7. `StringBuilder` y `reverse()`

Como estamos recorriendo los números de derecha a izquierda, el resultado se construye al revés.

Por ejemplo:

```text
123 + 456
```

primero obtenemos:

```text
9
7
5
```

es decir:

```text
"975"
```

Por eso usamos:

```java
result.reverse()
```

para obtener:

```text
"579"
```

`StringBuilder` fue una buena elección porque estamos agregando caracteres progresivamente.

---

# 8. Y finalmente aparecieron los ceros iniciales

Los últimos tests fueron muy interesantes:

```text
a = "00103"
b = "08567"
```

Tu algoritmo obtuvo:

```text
"08670"
```

pero el test esperaba:

```text
"8670"
```

Y aquí hay algo importante:

### **La suma ya estaba bien.**

El problema ahora era únicamente de representación.

Lo mismo ocurrió con:

```text
064166807079189962044856801417
```

cuando se esperaba:

```text
64166807079189962044856801417
```

El algoritmo había hecho correctamente la suma, pero conservaba el `0` inicial.

Por eso añadimos el último paso para eliminar ceros innecesarios:

```java
return resultString.replaceFirst("^0+(?!$)", "");
```

Con esto:

```text
"000123" → "123"
"00103"  → "103"
"08670"  → "8670"
"0000"   → "0"
```

El último caso es importante porque no queremos devolver un String vacío cuando el resultado es cero.

---

# 9. Evaluación de la solución final

La solución que terminaste construyendo tiene una complejidad de:

**Tiempo: `O(n)`**

porque cada dígito se procesa una vez.

**Espacio: `O(n)`**

porque se necesita almacenar el resultado.

Y lo más importante: **ya no depende del tamaño máximo de `Integer` o `Long`**.

Puede trabajar con números enormes porque realmente nunca almacena el número completo como un tipo numérico.

---

## Mi evaluación de tu proceso

Yo le pondría aproximadamente:

| Aspecto                         | Evaluación                        |
| ------------------------------- | --------------------------------- |
| Comprensión inicial             | 🟢 Buena                          |
| Primera solución                | 🟢 Correcta para números pequeños |
| Lectura de restricciones        | 🟡 Se tuvo que corregir           |
| Manejo de números grandes       | 🟢 Resuelto                       |
| Manejo de `carry`               | 🟢 Correcto                       |
| Manejo de diferentes longitudes | 🟢 Correcto                       |
| Manejo de ceros iniciales       | 🟢 Corregido mediante tests       |
| Complejidad                     | 🟢 `O(n)`                         |
| Uso de `StringBuilder`          | 🟢 Adecuado                       |
| Capacidad de depuración         | 🟢 Buena                          |

### Lo que más destacaría

El aprendizaje importante de este Kata **no es simplemente aprender a usar `StringBuilder` o `%`**.

Es aprender a pasar de:

> "¿Cómo hago que Java haga la operación?"

a:

> **"¿Qué algoritmo necesito dadas las restricciones del problema?"**

Tu primera solución delegaba todo a Java:

```java
Integer.valueOf(a) + Integer.valueOf(b)
```

La solución final entiende realmente **cómo funciona la suma** y la implementa independientemente del tamaño del número.

Ese cambio de enfoque es justamente lo valioso del ejercicio.

*/