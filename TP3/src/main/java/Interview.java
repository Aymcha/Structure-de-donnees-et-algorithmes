public class Interview {

    /** Expliquez votre complexité temporelle et spatiale en cas moyen et en pire cas à l'aide de commentaire dans le code
     *  Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     *
     ** TODO Time Complexity : Worst Case O(n), explain Worst and Average Case
     ** TODO Space Complexity : Determine and Explain Worst and Average Case in comments
     ** TODO HAS TO BE ITERATIVE, , NOT RECURSIVE
     * @param numbers List of numbers sorted in ascending order containing 1 non-duplicate
     * @return non-duplicate number
     */
    /*
     La complexité spatiale au moyen et au pire cas est : O(1+1)=>O(1) (size et la valeur de retour de la fonction)
     La complexite temporelle au pire cas est: O(1+n*(1+1)+1+1+1)=>O(4+2n)=>O(n)
     La complexite temporelle au moyen cas;
        Pour la boucle for: O((1+3+5+7+...+n)/n)=>O(n*n/n)=>O(n)
        Alors: la complexite moyenne est donc: O(1+n+3)=>O(n)
      
     */
    
    public static Integer findNonDuplicateIterativeLinear(Integer[] numbers) {
        int size = numbers.length;
        for (int i = 0; i < size - 1; i += 2) {
            if (!numbers[i].equals(numbers[i + 1])) return numbers[i];
        }
        if (size%2==1) return numbers[size-1];
        return null;
    }

    /** Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     *  Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     *
     ** TODO Time Complexity : Worst Case O(log(n)), explain Worst and Average Case
     ** TODO Space Complexity : Determine and Explain Worst and Average Case in comments
     ** TODO HAS TO BE ITERATIVE, NOT RECURSIVE
     * @param numbers List of numbers sorted in ascending order containing 1 duplicate
     * @return non-duplicate number
     */
     /*
     La complexité spatiale au moyen et au pire cas est : O(1+1+1+1+1)=>O(1) (size, minimum, high, center et
     la valeur de retour de la fonction).
     La complexité temporelle au pire cas et a au moyen cas  est: O(4+log(n)+1)=>O(log(n))
     (car au niveau du while, à chaque fois on divise notre champ de possibilites par 2, ce qui fait
      n/2^k au total, ce qui nous donne une complexite de O(log(n)))


     */
    public static Integer findNonDuplicateIterative(Integer[] numbers) {
        int size = numbers.length;
        int minimum = 0;
        int high = size-1;
        int center;
        while (minimum <= high) {
            center = (high + minimum) / 2;
            if (minimum == high) return numbers[center];
            if (center % 2 != 0) {
                if (!numbers[center].equals(numbers[center - 1])) {
                    high = center;
                } else {
                    minimum = center + 1;
                }
            } else {
                if (!numbers[center].equals(numbers[center + 1])) {
                    high = center;
                } else {
                    minimum = center + 1;
                }
            }
        }
        return null;
    }


    /** Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     *  Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     *
     ** TODO Time Complexity : Worst Case O(log(n)), explain Worst and Average Case
     ** TODO Space Complexity : Determine and Explain Worst and Average Case in comments
     ** TODO HAS TO BE RECURSIVE, NOT ITERATIVE
     * @param numbers List of numbers sorted in ascending order containing 1 non-duplicate
     * @return non-duplicate number
     */
     /*
      La complexité temporelle au pire et au moyen cas :
          Déterminons tout d'abord la complexité de recursiveBinarySearch est:
          T(n)= constante+T(N/2) et T(N/2)=constante+T(N/4)
          alors: T(N)=2*constante+T(N/4) et T(N/4)=T(N/8)+constante
          alors: T(N)=3*constante+T(N/8) ...
          finalement: T(N)=T(N/2^i)+i*c
          et: T(N/2^i)=T(1)
          alors:N/2^i=1, donc: N=2^i
          alors: log(N)=log(2^i)
          càd: log(n)=i
          on aura:T(N)=T(N/2^log(n))+log(n)*c =T(1)+log(n)*c
          donc: la complexité de recursiveBinarySearch est: O(log(n)).
          conclusion: la complexité temporelle de findNonDuplicateRecursive est:
          O(1+log(n))=O(log(n)
       La complexité spatiale au moyen et au pire cas est : O(log(n)).En effet, on aura log(n) appels de
       la fonction récursive qui seront tous sauvegardés dans la pile d'appel.

     */
    public static Integer findNonDuplicateRecursive(Integer[] numbers) {
        int size = numbers.length;
        return recursiveBinarySearch(numbers, 0, size-1);
    }

    public static Integer recursiveBinarySearch(Integer[] numbers, int minimum, int high){
        if (high >= minimum) {
            int center = (high + minimum) / 2;
            if (minimum != high) {
                if (center % 2 == 0) {
                    if (!numbers[center].equals(numbers[center + 1]))
                        return recursiveBinarySearch(numbers, minimum, center);
                    else
                        return recursiveBinarySearch(numbers, center + 1, high);
                } else {
                    if (numbers[center].equals(numbers[center - 1]))
                        return recursiveBinarySearch(numbers, center + 1, high);
                     else
                        return recursiveBinarySearch(numbers, minimum, center);
                }
            } else return numbers[center];
        }
        return null;
    }
}
