public class HumanReadableTime {
    public static void main(String[] args) {
        /*Instrucción
        Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)

        HH = hours, padded to 2 digits, range: 00 - 99
        MM = minutes, padded to 2 digits, range: 00 - 59
        SS = seconds, padded to 2 digits, range: 00 - 59
        The maximum time never exceeds 359999 (99:59:59)

        You can find some examples in the test fixtures.
        */
    }

    public static String makeReadable(int seconds) {
        int hrs = seconds/3600;
        int minutes = (seconds-hrs*3600)/60;
        int sec = seconds%60;
        StringBuilder exit = new StringBuilder();
        if (hrs<= 9) {
            exit.append("0"+hrs+":");
        }else{
            exit.append(hrs+":");
        }
        if (minutes<= 9) {
            exit.append("0"+minutes+":");
        }else{
            exit.append(minutes+":");
        }
        if (sec<= 9) {
            exit.append("0"+sec);
        }else{
            exit.append(sec);
        }

        return exit.toString();
    }
}
/*Retroalimentación
Sí, tu solución es **correcta y bastante buena** para este Kata. De hecho, la lógica que utilizaste para separar horas, minutos y segundos es adecuada. Hay, eso sí, algunas cosas que puedes mejorar para hacerla más limpia, idiomática y ligeramente más eficiente.

### 1. La conversión de tiempo está bien

Esta parte:

```java
int hrs = seconds / 3600;
int minutes = (seconds - hrs * 3600) / 60;
int sec = seconds % 60;
```

Funciona perfectamente.

Por ejemplo, con `3661`:

* `3661 / 3600 = 1` hora
* `3661 - 3600 = 61`
* `61 / 60 = 1` minuto
* `3661 % 60 = 1` segundo

Resultado:

```text
01:01:01
```

Aunque hay una pequeña mejora posible:

```java
int minutes = (seconds / 60) % 60;
```

Es más directo porque estás diciendo literalmente: "obtén los minutos totales y quédate con los minutos que sobran después de las horas".

---

### 2. Tu manejo de los ceros funciona, pero estás repitiendo bastante código

Tienes:

```java
if (hrs<= 9) {
    exit.append("0"+hrs+":");
}else{
    exit.append(hrs+":");
}
```

Y después prácticamente lo mismo para minutos y segundos.

No está mal, pero Java tiene una herramienta precisamente para esto: `String.format()`.

Podrías hacer:

```java
return String.format("%02d:%02d:%02d", hrs, minutes, sec);
```

`%02d` significa:

* `%d` → número entero
* `2` → mínimo 2 caracteres
* `0` → rellena con `0`

Así:

```java
1  → 01
9  → 09
10 → 10
```

Por lo que toda tu construcción del `StringBuilder` y los `if` desaparece.

---

### 3. En realidad no necesitas `StringBuilder`

Tu uso de:

```java
StringBuilder exit = new StringBuilder();
```

no es incorrecto, pero aquí no aporta mucho.

`StringBuilder` es especialmente útil cuando vas concatenando muchas cadenas en un proceso repetitivo, por ejemplo dentro de un ciclo.

Para **tres valores fijos**, `String.format()` resulta mucho más claro.

Tu solución:

```java
StringBuilder exit = new StringBuilder();

if (...) {
    ...
}

if (...) {
    ...
}

if (...) {
    ...
}

return exit.toString();
```

puede reducirse a:

```java
return String.format("%02d:%02d:%02d", hrs, minutes, sec);
```

---

### 4. También puedes mejorar la concatenación

Tienes cosas como:

```java
exit.append("0"+hrs+":");
```

Esto funciona, pero estás haciendo una concatenación de `String` antes de pasársela a `append()`.

Sería más apropiado:

```java
exit.append("0").append(hrs).append(":");
```

Pero en este caso concreto, como vimos, ni siquiera necesitas hacerlo porque `String.format()` resuelve el problema.

---

## ¿Cuál considero la mejor versión?

Para este Kata, yo escribiría:

```java
public static String makeReadable(int seconds) {
    int hrs = seconds / 3600;
    int minutes = (seconds / 60) % 60;
    int sec = seconds % 60;

    return String.format("%02d:%02d:%02d", hrs, minutes, sec);
}
```

Es mucho más compacta y, sobre todo, **expresa directamente la intención del problema**.

### Comparación

| Aspecto                | Tu solución              | Versión mejorada |
| ---------------------- | ------------------------ | ---------------- |
| Correcta               | ✅                        | ✅                |
| Legible                | 🟢                       | 🟢🟢🟢           |
| Repetición             | Hay bastante             | Muy poca         |
| `StringBuilder`        | Innecesario aquí         | ❌                |
| `if` para padding      | Necesarios en tu enfoque | ❌                |
| Uso idiomático de Java | 🟢                       | 🟢🟢🟢           |
| Complejidad            | O(1)                     | O(1)             |

**Complejidad:** ambas son `O(1)` en tiempo y `O(1)` en espacio, así que no estás ante un problema de optimización algorítmica. La mejora principal es de **claridad y simplicidad del código**.

Y algo importante: **tu solución no es "mala" por ser más larga**. Al contrario, demuestra que entendiste cómo construir el formato manualmente. Ahora que ya sabes hacerlo, el siguiente paso es reconocer cuándo Java ya tiene una herramienta que te permite expresar esa misma lógica de forma más limpia.

*/