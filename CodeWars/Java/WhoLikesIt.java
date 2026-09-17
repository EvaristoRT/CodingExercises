public class WhoLikesIt {
    public static void main(String[] args) {
        /*Instrucción
        You probably know the "like" system from Facebook and other pages. People can "like" blog posts, pictures or other items. We want to create the text that should be displayed next to such an item.

        Implement the function which takes an array containing the names of people that like an item. It must return the display text as shown in the examples:

        []                                -->  "no one likes this"
        ["Peter"]                         -->  "Peter likes this"
        ["Jacob", "Alex"]                 -->  "Jacob and Alex like this"
        ["Max", "John", "Mark"]           -->  "Max, John and Mark like this"
        ["Alex", "Jacob", "Mark", "Max"]  -->  "Alex, Jacob and 2 others like this"
        Note: For 4 or more names, the number in "and 2 others" simply increases.
        */
    }

    public static String whoLikesIt(String... names) {
        int arrayLenght = names.length;

        switch (arrayLenght) {
            case 0:
                return "no one likes this";
            case 1:
                return names[0]+" likes this";
            case 2:
                return names[0] + " and " + names[1] + " like this";
            case 3:
                return names[0] + ", " + names[1] + " and " + names[2] + " like this";
            default:
                return names[0] + ", " + names[1] + " and " + (arrayLenght-2) + " others like this";
        }
    }
}
/*Retroalimentación
Tu solución está **bien planteada y es correcta** para el ejercicio de Codewars. De hecho, para este problema tu enfoque con `switch` es bastante claro porque existen exactamente 5 casos de comportamiento.

### ✅ Lo que hiciste bien

**1. La lógica cubre todos los casos**

Tu `switch` contempla:

* `0` nombres → `"no one likes this"`
* `1` nombre → `"Peter likes this"`
* `2` nombres → `"Peter and Alex like this"`
* `3` nombres → `"Peter, Alex and Mark like this"`
* `4+` nombres → `"Peter, Alex and 2 others like this"`

Especialmente el `default` está bien resuelto:

```java
(arrayLenght - 2)
```

¿Por qué `-2`? Porque muestras los **primeros dos nombres** y todos los demás se agrupan como `"others"`.

Por ejemplo:

```text
4 nombres → 4 - 2 = 2 others
5 nombres → 5 - 2 = 3 others
10 nombres → 10 - 2 = 8 others
```

---

### ✅ 2. Elegiste correctamente `String...`

Esto:

```java
public static String whoLikesIt(String... names)
```

es una buena elección. Permite llamar al método de ambas formas:

```java
whoLikesIt("Peter", "Alex");
```

o:

```java
String[] names = {"Peter", "Alex"};
whoLikesIt(names);
```

En este ejercicio funciona perfectamente porque `String...` internamente se maneja como un `String[]`.

---

### ✅ 3. `switch` es perfectamente válido aquí

No necesitas complicarlo con varios `if/else`.

Tu estructura:

```java
switch (arrayLenght) {
    case 0:
    case 1:
    case 2:
    case 3:
    default:
}
```

representa directamente las reglas del problema.

Para este ejercicio, yo consideraría tu solución **simple, legible y eficiente**.

---

## 🔧 Cosas que podrías mejorar

### 1. `arrayLenght` tiene un typo

Tienes:

```java
int arrayLenght = names.length;
```

Debería ser:

```java
int arrayLength = names.length;
```

`Length` está escrito `Lenght`.

No afecta absolutamente nada al funcionamiento, pero sí conviene corregirlo porque estás trabajando en código que eventualmente podría formar parte de proyectos reales.

---

### 2. No necesitas guardar `names.length`

Podrías directamente hacer:

```java
switch (names.length) {
```

en lugar de:

```java
int arrayLength = names.length;

switch (arrayLength) {
```

Pero aquí hay una cuestión de estilo.

Tu versión:

```java
int arrayLength = names.length;
```

hace que el `switch` sea ligeramente más fácil de leer y después reutilizas el valor en `default`:

```java
arrayLength - 2
```

Así que **no considero que sea un problema**. De hecho, en este caso tiene sentido conservar la variable.

---

### 3. Espaciado

Tienes:

```java
names[0]+" likes this"
```

Java permite esto, pero convencionalmente se escribe:

```java
names[0] + " likes this"
```

Lo mismo para las demás concatenaciones.

Es un detalle pequeño, pero mejora bastante la legibilidad.

---

## ⚡ Complejidad

Tu solución es muy eficiente.

**Tiempo: O(1)**

No recorres el arreglo. Solamente:

* consultas `names.length`
* accedes a como máximo 3 posiciones
* construyes el `String`

Incluso si recibieras 1,000,000 de nombres, no necesitarías recorrerlos para saber que son:

```text
Nombre1, Nombre2 and 999998 others like this
```

**Espacio adicional: O(1)**

No creas estructuras adicionales como `List`, `Map`, etc.

---

## 🧠 Algo importante sobre tu solución

Creo que este ejercicio es un buen ejemplo de que **"más corto" no necesariamente significa "más óptimo"**.

Podrías intentar reducirlo muchísimo usando operadores ternarios o alguna lógica más compacta, pero probablemente terminarías con algo más difícil de leer.

Tu solución:

```java
switch (arrayLength) {
    case 0:
        ...
    case 1:
        ...
    case 2:
        ...
    case 3:
        ...
    default:
        ...
}
```

hace que las reglas del problema sean prácticamente visibles directamente en el código.

Para un ejercicio de Codewars, **me parece una muy buena solución**.

### Mi valoración

| Aspecto             | Evaluación                |
| ------------------- | ------------------------- |
| Correctitud         | ✅ Correcta                |
| Complejidad         | ✅ O(1)                    |
| Legibilidad         | ✅ Buena                   |
| Estructura          | ✅ Buena                   |
| Uso de `switch`     | ✅ Apropiado               |
| Uso de `String...`  | ✅ Correcto                |
| Optimización        | ✅ Ya es muy eficiente     |
| Detalles a corregir | `arrayLenght` + espaciado |

**Conclusión:** no veo ninguna mejora algorítmica importante que necesites hacer. Yo conservaría el enfoque y únicamente corregiría los detalles de estilo/nombre.

*/