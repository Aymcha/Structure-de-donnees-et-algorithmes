import java.util.*;

public final class Interview {
    /**
     * Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     * Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     * <p>
     * * TODO Time Complexity  :
     * * TODO Space Complexity :
     *
     * @param boxes The array that contains the weight of each box.
     * @return The weight of the final box if it is applicable.
     */
    /* la compléxité temporelle est de : O(1+1+n+n*log(n)+n*(3log(n))+1+1)
                                          => O(4+n+4nlog(n))
                                          => O(max(4,n,4nlog(n))
                                          =>O(nlog(n))
       En ce qui concerne la complexité spatiale:
        On initialise une PriorityQueue vide, la complexité spatiale est donc O(1)
        On ajoute n élements (boucle for) à notre PriorityQueue, la complexité spatiale est donc O(n) dans ce cas.
        Dans notre boucle while, la complexité spatiale au pire cas est O(n)
        Notre méthode a une valeur de retour, sa compléxité spatiale est O(1) (on retourne un seul élement).
        La complexité spatiale totale est donc: O(1+n+n+1)
                                               =>O(2n+2)
                                               =>O(2n)
                                               =>O(n)



     */
    static public int lastBox(int[] boxes) {
        if (boxes.length == 0) return 0; //complexité temporelle : O(1)
        int firstBoxe, secondBoxe; //complexité temporelle : O(1)
        PriorityQueue<Integer> queueOfBoxes = new PriorityQueue<>(Collections.reverseOrder()); //complexité temporelle : O(n)
        for (int i : boxes) //complexité temporelle : O(n)
            queueOfBoxes.add(i);//complexité temporelle : O(log(n))
        while (queueOfBoxes.size() > 1) { //complexité temporelle :O(n)
            firstBoxe = queueOfBoxes.poll(); //complexité temporelle : O(log(n))
            secondBoxe = queueOfBoxes.poll(); //complexité temporelle : O(log(n))
            if (firstBoxe - secondBoxe != 0) queueOfBoxes.add(firstBoxe - secondBoxe); //complexité temporelle : O(log(n))
        }
        if (queueOfBoxes.size() == 0) return 0; //complexité temporelle : O(1)
        return queueOfBoxes.peek();   //complexité temporelle : O(1)
    }
}