import java.util.*;
import java.util.HashMap;

public final class Interview {

    /** Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     *  n représente le nombre de charactère de `phrase` et m le nombre de charactère de `stopwords`
     *  Indiquez les équivalences telles que O(n + 1 + m + 1) => O(n+m) et O(2n+3m) => O(n+m) lorsque possible
     *
     ** TODO Justify Time Complexity  : Average Case O(n+m)
     ** TODO Justify Space Complexity : Worst Case O(n+m)
     *
     * @param phrase String containing a sequence of words separated by a space
     * @param stopwords String array containing all the stop words
     * @return Pair containing two elements, first being the most common word not in the stop words,
     * second being the number of occurences of this word
     */

    // Temporel: O(1 + 1 + n + n + m + 1 + 1 + n + 1 + 1) => O(3n + m + 6) => O(n + m)
    // Space: O(n + m + (n - n) + n + n + 1) => O(n + m + n + 1) => O(n + m)

    public static Pair findMostCommonValidWord(String phrase, String[] stopwords) { // Space: n + m
        HashMap<String, Pair> wordsCount = new HashMap<>(); // Temporel: 1 || Space: 1
        if (phrase.length() != 0) { // Temporel: 1
            phrase = phrase.toLowerCase(Locale.ROOT); // Temporel: n || Space: n - n
            // Space: On déassigne et on réassigne la variable phrase avec une valeur de même grandeur
            String[] words = phrase.split(" "); // Temporel: n || Space: n

            // Temporel: Pour la boucle for suivante O(n * 1 * 1 * 1) => O(n)
            // Space: WordsMap devient O(n * 1) => O(n)
            for (String w : words) { // Temporel: n
                // Temporel: techniquement ça serait O(n / (nb de " " + 1 dans phrase)) mais ça revient à une complexité
                // de O(n)
                if (!wordsCount.containsKey(w)) { // Temporel: 1
                    wordsCount.put(w, new Pair(w, 1)); // Temporel: 1 || Space: 1
                } else {
                    wordsCount.get(w).second++; // Temporel: 1
                }
            }

            // Temporel: Pour la boucle for suivante O(m * 1) => O(m)
            for (String w : stopwords) { // Temporel: m
                // Temporel: techniquement ça serait O(m / (nb de " " + 1 dans phrase)) mais ça revient à une complexité
                // de O(m)
                wordsCount.remove(w.toLowerCase(Locale.ROOT)); // Temporel: 1
                // Temporel: Nous avons supposé que le string x était une chaîne de caractère de longueur fixe qui
                // n'est pas très grande puisqu'elle repésente un mot. Ainsi, même si la méthode toLowerCase
                // est de O(n) [n représentant la longueur de x], nous avons pu conclure que la méthode est 0(1)
            }
        }

        Pair highestValue = new Pair("", 0); // Temporel: 1 || Space: 1
        if (!wordsCount.isEmpty()) { // Temporel: 1
            // Temporel: Pour la boucle for suivante O(n * 1 * 1) => O(n)
            for (Pair w : wordsCount.values()) { // Temporel: n
                // Temporel: techniquement ça serait O(n / (nb de " " + 1 dans phrase)) mais ça revient à une complexité
                // de O(n)
                if (w.second > highestValue.second) { // Temporel: 1
                    highestValue = w; // Temporel: 1 || Space: 1 - 1 (On désassigne et réassigne par la suite)
                }
            }
        } else {
            highestValue = new Pair(null, null); // Temporel: 1 || Space: 1 - 1 (On désassigne et réassigne par la suite)
        }
        return highestValue; // Temporel: 1
    }
}
