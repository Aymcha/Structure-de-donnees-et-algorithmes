package Alphabet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Alphabet {

    /**
     * TODO
     * From the words contained in the dictionary of a fictitious language, find the lexical order of
     * the symbols composing the language.
     *
     * @param dictionary Contains all the word of a language
     * @return The lexicalOrder of the symbols composing this language
     */
    public static <ValueType> ArrayList<Character> lexicalOrder(String[] dictionary) {
        ArrayList<Character> lexicalOrder = new ArrayList<>();
        Graph<Character> graph = new Graph<Character>();
        for (int i = 0; i < dictionary.length - 1; i++) {
            String firstWord = dictionary[i];
            String secondWord = dictionary[i + 1];
            for (int j = 0; j < firstWord.length(); j++) {
                if (firstWord.charAt(j) != secondWord.charAt(j)) {
                    graph.connect(firstWord.charAt(j), secondWord.charAt(j));
                    break;
                }
            }
        }
        Queue<Vertex<Character>> adjacent = new LinkedList<>();
        for (Vertex<Character> element : graph.vertices) {
            if (element.indegree == 0)
                adjacent.add(element);
        }
        while (adjacent.size() > 0) {
            Vertex<Character> node = adjacent.poll();
            lexicalOrder.add((Character) node.label);
            for (Vertex<Character> a: node.toVertex) {
                a.indegree--;
                if (a.indegree == 0)
                    adjacent.add(a);
            }
            graph.vertices.remove(node);
        }
        return lexicalOrder;
    }
}


