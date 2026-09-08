public class DoesMyNumberLookBigInThis {
    public static void main(String[] args) {
        /*Instrucción
        A Narcissistic Number (or Armstrong Number) is a positive number which is the sum of its own digits, each raised to the power of the number of digits in a given base. In this Kata, we will restrict ourselves to decimal (base 10).

        For example, take 153 (3 digits), which is narcissistic:
            1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
        and 1652 (4 digits), which isn't:
            1^4 + 6^4 + 5^4 + 2^4 = 1 + 1296 + 625 + 16 = 1938
        The Challenge:

        Your code must return true or false (not 'true' and 'false') depending upon whether the given number is a Narcissistic number in base 10.
        This may be True and False in your language, e.g. PHP.
        Error checking for text strings or other invalid inputs is not required, only valid positive non-zero integers will be passed into the function.
                */
    }
    public static boolean isNarcissistic(int number) {
        String numberString = String.valueOf(number);
        int sum = 0;
        for(int i = 0; i<numberString.length(); i++){
            sum += Math.pow(Character.getNumericValue(numberString.charAt(i)), numberString.length());
        }

        if (sum == number) {
            return true;
        }
        return false;
    }
}
/*Retroalimentación 
La solución está **correcta y bastante limpia** para el nivel del ejercicio. De hecho, la lógica principal está bien planteada: convertir el número a `String`, recorrer sus dígitos, elevar cada uno a la cantidad de dígitos y acumular la suma.

### Lo que está bien

```java
String numberString = String.valueOf(number);
```

Es una buena decisión porque permite obtener fácilmente cada dígito mediante `charAt()`.

También está bien:

```java
Character.getNumericValue(numberString.charAt(i))
```

Convierte correctamente el `char` (`'5'`) en su valor numérico (`5`).

Y esta parte representa perfectamente la definición del número narcisista:

```java
sum += Math.pow(
    Character.getNumericValue(numberString.charAt(i)),
    numberString.length()
);
```

Por ejemplo, con `153`:

```text
1³ + 5³ + 3³
= 1 + 125 + 27
= 153
```

---

## 🔧 Un detalle importante: `Math.pow()`

Aquí hay algo que conviene tener en cuenta:

```java
Math.pow(...)
```

**devuelve un `double`**, mientras que `sum` es `int`.

Java está permitiendo la asignación porque en tu expresión:

```java
sum += Math.pow(...)
```

el operador compuesto `+=` hace una conversión implícita.

Aunque funciona para este Kata, conceptualmente sería más apropiado dejar explícito que el resultado debe ser entero:

```java
sum += (int) Math.pow(
    Character.getNumericValue(numberString.charAt(i)),
    numberString.length()
);
```

---

## 🟢 También se puede simplificar el `if`

Actualmente:

```java
if (sum == number) {
    return true;
}
return false;
```

Se puede reducir directamente a:

```java
return sum == number;
```

Esto es bastante común en Java porque `sum == number` **ya produce un `boolean`**.

Entonces:

```java
public static boolean isNarcissistic(int number) {
    String numberString = String.valueOf(number);
    int sum = 0;

    for (int i = 0; i < numberString.length(); i++) {
        sum += (int) Math.pow(
            Character.getNumericValue(numberString.charAt(i)),
            numberString.length()
        );
    }

    return sum == number;
}
```

---

## 💡 ¿Hay una solución más eficiente?

Sí. Se puede resolver **sin convertir el número a `String`**, utilizando operaciones matemáticas:

```java
int digits = String.valueOf(number).length();
int sum = 0;
int temp = number;

while (temp > 0) {
    int digit = temp % 10;
    sum += Math.pow(digit, digits);
    temp /= 10;
}

return sum == number;
```

Pero no diría que sea necesariamente "mejor" para este ejercicio.

La solución con `String` tiene una ventaja importante: **es muy fácil de leer y entender**. Para un Kata de práctica, eso tiene bastante valor.

### Evaluación general

| Aspecto     | Evaluación |
| ----------- | ---------- |
| Lógica      | ⭐⭐⭐⭐⭐      |
| Legibilidad | ⭐⭐⭐⭐⭐      |
| Uso de Java | ⭐⭐⭐⭐       |
| Eficiencia  | ⭐⭐⭐⭐       |
| Simplicidad | ⭐⭐⭐⭐⭐      |

**Lo principal que mejoraría:** el `if` final y hacer explícito el casteo de `Math.pow()`.

Por lo demás, es una solución bastante sólida.

*/