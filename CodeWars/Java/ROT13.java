import java.util.ArrayList;

public class ROT13 {
    public static void main(String[] args) {
        /*Instrucción
        How can you tell an extrovert from an introvert at NSA?
        Va gur ryringbef, gur rkgebireg ybbxf ng gur BGURE thl'f fubrf.

        I found this joke on USENET, but the punchline is scrambled. Maybe you can decipher it?
        According to Wikipedia, ROT13 is frequently used to obfuscate jokes on USENET.

        For this task you're only supposed to substitute characters. Not spaces, punctuation, numbers, etc.

        Test examples:

        "EBG13 rknzcyr." -> "ROT13 example."

        "This is my first ROT13 excercise!" -> "Guvf vf zl svefg EBG13 rkprepvfr!"
        */
    }
    public static String rot13(String message) {
        char[] messageArray = message.toCharArray();
        StringBuilder finalMessage = new StringBuilder();
        for (char letter : messageArray){
            char encrypted;
            if (letter >= 'A' && letter <= 'Z') {
                encrypted = (char) ('A' + (letter - 'A' + 13) % 26);
            } else if (letter >= 'a' && letter <= 'z') {
                encrypted = (char) ('a' + (letter - 'a' + 13) % 26);
            } else {
                encrypted = letter;
            }
            
            finalMessage.append(encrypted);
        }
        return finalMessage.toString();
    }
}
/*Retroalimentación
La solución está **bien planteada y ya es bastante eficiente**. Para este ejercicio de Codewars, no hay una necesidad real de usar `HashMap` ni estructuras adicionales. La lógica que se utilizó es apropiada.

### Retroalimentación

**1. `StringBuilder` fue una buena elección**

```java
StringBuilder finalMessage = new StringBuilder();
```

Es mejor que utilizar un `String` e ir concatenando:

```java
finalMessage += encrypted;
```

porque `String` es inmutable y se estarían creando nuevos objetos continuamente. `StringBuilder` está diseñado justamente para construir cadenas progresivamente.

---

**2. `char[] messageArray` es innecesario**

Actualmente se hace:

```java
char[] messageArray = message.toCharArray();

for (char letter : messageArray)
```

Pero `String` ya puede recorrerse directamente con un `for` usando `charAt()`:

```java
for (int i = 0; i < message.length(); i++) {
    char letter = message.charAt(i);
}
```

Aunque, personalmente, **no consideraría tu versión peor**. `toCharArray()` hace que el `for-each` quede bastante limpio y legible.

Incluso podrías dejarlo así:

```java
for (char letter : message.toCharArray()) {
```

y eliminar completamente:

```java
char[] messageArray = message.toCharArray();
```

---

**3. La comprobación de caracteres está muy bien**

Esta parte es probablemente lo más importante de la solución:

```java
if (letter >= 'A' && letter <= 'Z') {
```

```java
else if (letter >= 'a' && letter <= 'z') {
```

```java
else {
    encrypted = letter;
}
```

Esto respeta exactamente la instrucción del ejercicio: **solo modificar letras y conservar espacios, números y puntuación**.

Además, separar mayúsculas y minúsculas evita tener que utilizar métodos adicionales como `Character.isUpperCase()`.

---

**4. La fórmula ROT13 está correctamente planteada**

```java
(char) ('A' + (letter - 'A' + 13) % 26)
```

y:

```java
(char) ('a' + (letter - 'a' + 13) % 26)
```

Es una solución matemática bastante elegante porque el `% 26` permite que el alfabeto sea circular:

```text
A → N
B → O
...
M → Z
N → A
...
Z → M
```

No hace falta ningún `HashMap`, `ArrayList` ni una tabla de equivalencias.

---

### Una versión ligeramente más limpia

Manteniendo exactamente la misma lógica:

```java
public static String rot13(String message) {
    StringBuilder finalMessage = new StringBuilder();

    for (char letter : message.toCharArray()) {
        if (letter >= 'A' && letter <= 'Z') {
            letter = (char) ('A' + (letter - 'A' + 13) % 26);
        } else if (letter >= 'a' && letter <= 'z') {
            letter = (char) ('a' + (letter - 'a' + 13) % 26);
        }

        finalMessage.append(letter);
    }

    return finalMessage.toString();
}
```

Aquí se elimina la variable:

```java
char encrypted;
```

porque realmente **no es necesaria**. Si el carácter es una letra, se transforma; si no lo es, simplemente se deja como estaba.

### ¿Cuál consideraría mejor?

| Aspecto                  | Solución original | Versión simplificada |
| ------------------------ | ----------------- | -------------------- |
| Correcta                 | ✅                 | ✅                    |
| Complejidad              | O(n)              | O(n)                 |
| Memoria                  | O(n)              | O(n)                 |
| Legibilidad              | ⭐⭐⭐⭐              | ⭐⭐⭐⭐⭐                |
| Estructuras innecesarias | No                | No                   |
| Eficiencia               | Muy buena         | Muy buena            |

La diferencia de rendimiento es prácticamente irrelevante. **La principal mejora sería de limpieza, no de eficiencia.**

En general, para un ejercicio de práctica de lógica, la solución está bastante bien: se identificó correctamente que ROT13 puede resolverse mediante aritmética con caracteres en lugar de construir un diccionario de 26 elementos.

*/