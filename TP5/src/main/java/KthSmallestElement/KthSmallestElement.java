package KthSmallestElement;

import java.util.PriorityQueue;

public class KthSmallestElement {
    /**
     * Explication de votre complexité temporelle
     *
     * La complexité temporelle au meilleur cas, c'est-à-dire, si la matrice est nulle, la longueur de la matrice
     * est 0 ou si k est plus grand ou plus petit que la matrice, est de O(1). En effet, ce sont des variables qui
     * contiennent une seule valeur qui sont créees et utilisées.
     *
     * La complexité au pire cas est de O(max(m,n)). En effet, à part la priorityQueue utilisée, toutes les variables
     * utilisées sont des variables qui contiennent une seule donnée. Concernant la priorityQueue, sa complexité
     * temporelle est O(max(m,n), car elle contient en tout temps seulement un élément par ligne de la matrice.
     *
     * Explication de votre complexité spatiale
     *
     * La complexité au meilleur cas, c'est-à-dire, si la matrice est nulle, la longueur de la matrice
     * est 0 ou si k est plus grand ou plus petit que la matrice, est de O(1). C'est seulement des opérations constantes
     * qui sont effectuées.
     *
     * La complexité au pire cas est de O(klog(max(m,n)). En effet, la création de la priorityQueue vide
     * est de O(1). Après, l'ajout du premier élément dans la queue vide est de O(1), car c'est certain qu'il n'y
     * a aucun élément de la queue à déplacer, car on place le premier élément. Ensuite, la création des variables
     * n, m, lastElement et nRepititions sont de O(1). Par la suite, on entre dans une boucle qui se répète k fois.
     * On commence par incrémenter nRepititions ce qui est une opération constante O(1). Après on retire la première
     * valeur de la queue, ce qui est Olog(max(m,n)). En effet, l'opération poll d'une priorityQueue est de type
     * logarithmique et c'est le max(m,n), car il y a toujours seulement 1 élément par ligne de la matrice dans la
     * queue. Après, on assigne la valeur lastElement en O(1). Par la suite, on fait une comparaison en O(1).
     * Par la suite, si on respecte la condition, on ajoute un élément à la queue en O(log(max(m,n)).
     * En effet, l'opération add d'une priorityQueue est de type logarithmique et c'est le max(m,n),
     * car il y a toujours seulement 1 élément par ligne de la matrice dans la queue. Après,
     * on refait une comparaison en O(1) et si la condition est respectée, on refait un ajout à la queue
     * en O(log(max(m,n)). Ainsi, la complexité temporelle au pire cas est:
     *      5 * O(1) + k(O(1) + O(log(max(m,n))) + 2 * O(1) + O(log(max(m,n))) + O(1) + O(log(max(m,n)))
     *      = O(5 + k + klog(max(m,n)) + 2k + klog(max(m,n)) + k + klog(max(m,n))
     *      = O(5 + 4k + 3klog(max(m,n))
     *      = O(log(max(m,n)))
     */
    /** TODO Worst case
     *      Time complexity : O ( k log max(m,n) )
     *      Space complexity : O ( max(m,n) )
     *
     * Returns the `k`th smallest element in `matrix`
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j + 1]
     * @param k Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {
        if (matrix == null || matrix.length == 0 || k < 0 || k > matrix.length * matrix[0].length - 1) return null;
        PriorityQueue<Vertex<T>> nextElements = new PriorityQueue<>();
        nextElements.add(new Vertex<T>(0,0, matrix[0][0]));
        int n = matrix.length;
        int m = matrix[0].length;
        T lastElement = matrix[0][0];
        int nRepititions = 0;
        while (nRepititions <= k && nextElements.size() != 0) {
            nRepititions++;
            Vertex<T> node = nextElements.poll();
            lastElement = node.value;
            if (node.firstIndex + 1 < n) {
                nextElements.add(new Vertex<T>(node.firstIndex + 1, node.secondIndex, matrix[node.firstIndex + 1][node.secondIndex]));
            }
            if (node.secondIndex + 1 < m && node.firstIndex == 0) {
                nextElements.add(new Vertex<T>(node.firstIndex, node.secondIndex + 1, matrix[node.firstIndex][node.secondIndex + 1]));
            }
        }
        return lastElement;
    }
}
