

import java.util.HashMap;

public class DuplicateEncoder {
    public static void main(String[] args) {
        /*
            Instrucción
            The goal of this exercise is to convert a string to a new string where each character in the new string is "(" if that character appears only once in the original string, or ")" if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.

            Examples
            "din"      =>  "((("
            "recede"   =>  "()()()"
            "Success"  =>  ")())())"
            "(( @"     =>  "))((" 
            Notes
            Assertion messages may be unclear about what they display in some languages. If you read ...It Should encode XXX, the XXX is the expected result, not the input
        */
    }
    static String encode(String word){
        word = word.toLowerCase();
        StringBuilder finalWord = new StringBuilder();
        char[] chars = word.toCharArray();
        HashMap <Character, Integer> count = new HashMap<>();
        for(char letter: chars){
            if (count.containsKey(letter)){
                count.put(letter, count.getOrDefault(letter, 0) + 1);
            }
            else{
                count.put(letter, 1);
            }
        }
        for(int i = 0; i<word.length(); i++){
            if (count.get(chars[i]) > 1) {
                finalWord.append(")");
            }else{
                finalWord.append("(");
            }
        }
        String result = finalWord.toString();
        return result;
    }
}
/*Retroalimentacion
### 1. El `containsKey()` es innecesario

Se tiene:
if (count.containsKey(letter)){
    count.put(letter, count.getOrDefault(letter, 0) + 1);
}
else{
    count.put(letter, 1);
}

Aquí se está haciendo dos comprobaciones cuando `getOrDefault()` justamente existe para evitar eso.

Se podría reducirlo conceptualmente a:

count.put(letter, count.getOrDefault(letter, 0) + 1);

La primera vez:

getOrDefault('e', 0) → 0
0 + 1 → 1

La segunda:

getOrDefault('e', 0) → 1
1 + 1 → 2

Esto hace el código **más limpio y más idiomático de Java**.

---

### 2. No necesitas convertir `word` a `char[]`

Se tiene:

char[] chars = word.toCharArray();

No está mal, pero se podría recorrer directamente el `String`:

for (char letter : word.toCharArray()) {
    ...
}

Y luego hacer:

for (int i = 0; i < word.length(); i++) {
    ...
}

Sin embargo, en este caso `chars` **sí te resulta útil en el segundo `for`**, porque se hace:

count.get(chars[i])

Así que tampoco diría que está mal tenerlo.
De hecho, yo probablemente conservaría el `char[]` en esta solución.

### 3. El segundo `for` puede ser más limpio

Actualmente hace:

for(int i = 0; i<word.length(); i++){
    if (count.get(chars[i]) > 1) {
        finalWord.append(")");
    }else{
        finalWord.append("(");
    }
}

Como ya se tiene `chars`, puedes volver a usar el mismo estilo de `for-each`:

para cada carácter:
    consultar su contador
    si > 1 → )
    si = 1 → (

Eso elimina el índice `i`, que realmente no se necesita.

### 4. Se puede hacer el `return` directamente

Se tiene:

String result = finalWord.toString();
return result;

No está mal, pero `result` no aporta nada porque solamente lo utilizas una vez.

Se puede hacer:

return finalWord.toString();

## Una versión más limpia de esta misma solución

No es otro algoritmo; es prácticamente exactamente lo que se hizo, pero eliminando cosas innecesarias:

static String encode(String word) {
    word = word.toLowerCase();

    StringBuilder finalWord = new StringBuilder();
    char[] chars = word.toCharArray();

    HashMap<Character, Integer> count = new HashMap<>();

    for (char letter : chars) {
        count.put(letter, count.getOrDefault(letter, 0) + 1);
    }

    for (char letter : chars) {
        if (count.get(letter) > 1) {
            finalWord.append(")");
        } else {
            finalWord.append("(");
        }
    }

    return finalWord.toString();
}
```

Fíjate que **la lógica fundamental es la misma**. Solamente estamos haciendo que sea más concisa.

## ¿Y en cuanto a eficiencia?

Esta solución tiene una complejidad aproximada de:

**Tiempo: O(n)**
**Espacio: O(n)**

Porque se hacen dos recorridos sobre el `word`:

1er recorrido → contar
2º recorrido → construir resultado

Eso es completamente razonable.
 */
