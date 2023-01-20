import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;


    public AvlTree() {
    }

    /**
     * TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *  TODO: What's the average case? Briefly explain
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     * <p>
     * Note Perso : BinaryNode is null safe so no need to check when the node is null, we can directly add the element
     *
     * @param value value to add to the tree
     */
     /*
      Au niveau du premier while de notre méthode add, on divise à chaque fois notre champ de possibilités par 2
      (soit on passe au fils droit ou bien gauche du nœud jusqu'à pouvoir insérer le nouveau nœud).
      Au niveau du deuxième while, c'est le même principe à l'inverse. On part de l'enfant et on passe au parent.
      À chaque incrémentation, on divise en deux le champs de possibilité. Dans la deuxième boucle, il y a la fonction
      balance qui est appelé. Or, cette méthode est O(logn) seulement lorsqu'il faut rebalancer l'arbre. Lorsque l'arbre
      n'a pas besoin d'être rebalancé, la méthode est O(1). Considérant que la méthode balance sera une seule fois O(logn) dans
      la boucle et tous les autres fois O(1), on peut alors considérer que la deuxième boucle est O(logn) + O(logn).
      On aura alors pour les deux while une complexité moyenne de O(log(n)).
      La méthode contains et updateHeightNode est O(logn)
      Donc la complexité moyenne du add est: O(log(n)+4+log(n)+log(n)+1+log(n))=>O(5+4log(n))=>O(log(n))
      */
    public void add(ValueType value) {
        if(contains(value)) return;
        if(root == null) {
            root = new BinaryNode<>(value);
            return;
        }
        BinaryNode<ValueType> child = root;

        while (true) {
            if (value.compareTo(child.value) < 0) {
                if(child.left == null){
                    child.left = new BinaryNode<>(value);
                    child.left.parent = child;
                    child = child.left;
                    break;
                }
                child = child.left;
            }
            else if ((value.compareTo(child.value) > 0)) {
                if(child.right == null) {
                    child.right = new BinaryNode<>(value);
                    child.right.parent = child;
                    child = child.right;
                    break;
                }
                child = child.right;
            }
        }
        updateNodeHeight(child.parent);

        boolean balanceDone = false;
        while (child.parent != null && !balanceDone) {
            child = child.parent;
            BinaryNode<ValueType> parentOfChild = child.parent;
            balance(child);
            if (child.parent != parentOfChild) balanceDone = true;
        }
    }

    private BinaryNode<ValueType> findMin( BinaryNode<ValueType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    private BinaryNode<ValueType> remove( ValueType key, BinaryNode<ValueType> nodeToRemove )
    {
        if (key == root.value) root = null;
        if (nodeToRemove == null)
            return null;
        if (key.compareTo(nodeToRemove.value)<0)
            nodeToRemove.left = remove(key, nodeToRemove.left);
        else if (key.compareTo(nodeToRemove.value)>0)
            nodeToRemove.right = remove(key, nodeToRemove.right);
        else if (nodeToRemove.left != null && nodeToRemove.right != null) {
            nodeToRemove.value = findMin(nodeToRemove.right).value;
            nodeToRemove.right = remove( nodeToRemove.value, nodeToRemove.right );
        }
        else
            nodeToRemove = (nodeToRemove.left != null ) ? nodeToRemove.left : nodeToRemove.right;
        updateNodeHeight(nodeToRemove);
        if (nodeToRemove != null) balance(nodeToRemove);
        return nodeToRemove;
    }

    private void updateNodeHeight(BinaryNode<ValueType> node){
        if (node != null) {
            BinaryNode<ValueType> currentNode = node;
            while(currentNode != null) {
                if (currentNode.left == null) {
                    if (currentNode.right == null) currentNode.height = 0;
                    else currentNode.height = currentNode.right.height + 1;
                }
                else if (currentNode.right== null) {
                    currentNode.height = currentNode.left.height + 1;
                }
                else
                    currentNode.height = Math.max(currentNode.left.height + 1, currentNode.right.height + 1);
                currentNode = currentNode.parent;
            }
        }
    }
    /** TODO Worst case : O ( log n )
     *  TODO: What's the average case? Briefly explain
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    /*  Cette méthode fait appel à la méthode récursive
        remove(ValueType key, BinaryNode<ValueType> nodeToRemove). Cette dernière a une
        complexité moyenne de O(log(n)) car on fait simplement une recherche binaire sur notre arbre Avl
        en faisant chaque fois des appels de la fonction récursive sur le fils gauche ou bien droite du noeud
        en fonction de la valeur du noeud à retirer.Ce qui nous permet de diviser à chaque fois les possibilites totales par 2.
        La complexité moyenne de remove(ValueType value) est donc O(log(n))
     */
    public void remove (ValueType value){
        remove(value, root);
    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *  TODO: What's the average case? Briefly explain
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    /*
      Au niveau de la boucle while, on effectue une recherche binaire : tant qu'on n'a pas encore
      trouvé la valeur recherchée, on passe au fils gauche ou bien droit du noeud en fonction de la
      valeur recherché. Alors, on a une division par 2 à chaque fois.
      Donc la complexité moyenne du while est O(log(n)).
      On pourra donc dire que la complexité moyenne de cette méthode est: O(2+log(n)+1)=>O(log(n))
     */
    public boolean contains (ValueType value){
        int compareResult;
        BinaryNode<ValueType> nodeIterator = root;
        while(nodeIterator != null) {
            compareResult = value.compareTo(nodeIterator.value);
            if (compareResult == 0) return true;
            if (compareResult < 0) nodeIterator = nodeIterator.left;
            else nodeIterator = nodeIterator.right;
        }
        return false;
    }

    /** TODO Worst case : O( 1 )
     *  TODO: What's the average case? Briefly explain
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    /*
         La complexité moyenne de cette méthode est O(1) car cette méthode fait appel à
         getHeight(BinaryNode node) qui a une complexité moyenne de O(1).
     */
    public int getHeight () {
        return BinaryNode.getHeight(root);
    }

    /** TODO O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *  TODO: What's the average case? Briefly explain
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    /*
      Au niveau de la boucle while, on effectue une recherche binaire : tant qu'on n'est pas encore
      arrivé au plus petit noeud qui se trouve dans la partie gauche de notre AVl,
      On continue notre recherche. Alors, au niveau de chaque noeud, on passe directement au fils gauche.
      On divise chaque fois par 2. Donc la complexité moyenne du while est O(log(n)).
      On pourra donc dire que la complexité moyenne de cette méthode est: O(log(n)+4)=>O(log(n))
     */
    public ValueType findMin () {
        if(root == null) return null;
        BinaryNode<ValueType> child = root;
        while (child.left != null) child = child.left;
        return child.value;
    }

    /** TODO O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *  TODO: What's the average case? Briefly explain
     * Returns the node which has the maximum value contained in our root tree
     * @return Node which has the maximum value contained in our root tree
     */
     /*
      Au niveau de la boucle while, on effectue une recherche binaire : tant qu'on n'est pas encore
      arrivé au plus grand noeud qui se trouve dans la partie droite de notre AVl,
      On continue notre recherche. Alors, au niveau de chaque noeud, on passe directement au fils droit.
      Alors, on divise chaque fois par 2. Donc la complexité moyenne du while est O(log(n)).
      On pourra donc dire que la complexité moyenne de cette méthode est: O(log(n)+4)=>O(log(n))
     */
    public ValueType findMax () {
        if(root == null) return null;
        BinaryNode<ValueType> child = root;
        while (child.right != null) child = child.right;
        return child.value;
    }

    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *  TODO: What's the average case? Briefly explain
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    /*
      Comme cette méthode utilise infixOrder (BinaryNode < ValueType > currentNode, List < ValueType > items)
      de complexité moyenne O(n), alors la complexité moyenne de cette méthode est: O(3+n)=>O(n)
     */
    public List<ValueType> infixOrder () {
        List<ValueType> inOrderList = new ArrayList<>();
        BinaryNode<ValueType> currentNode = root;
        infixOrder(currentNode, inOrderList);
        return inOrderList;
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *  TODO: What's the average case? Briefly explain
     * Balances the whole tree
     * @param node Node to balance all the way to root
     */
    /*
     La complexité moyenne de cette méthode est O(logn). Comme la complexité des rotateLeft et rotateRight
     est de O(1) et que la complexité de updateHeightNode est de O(logn), alors la complexité du if est de O(1+1+1+1+1+1+O(logn)+O(logn))=>O(log(n))
      et celle de else if est aussi O(1+1+1+1+1+1+O(logn)+O(logn))=>O(log(n)). Alors, la complexité
     moyenne de cette méthode est: O(2+O(logn)+O(logn))=>O(log(n).
     */
    private void balance (BinaryNode < ValueType > node) {
        int leftNodeHeight = BinaryNode.getHeight(node.left);
        int rightNodeHeight = BinaryNode.getHeight(node.right);

        if ((rightNodeHeight - leftNodeHeight) >= 2 ) {
            if (BinaryNode.getHeight(node.right.right) >= BinaryNode.getHeight(node.right.left)) {
                rotateRight(node);
                updateNodeHeight(node.right);
                updateNodeHeight(node);
            }
            else {
                rotateLeft(node.right);
                updateNodeHeight(node.right.right);
                rotateRight(node);
                updateNodeHeight(node.left.left);
            }
        }
        else if ((leftNodeHeight - rightNodeHeight) >= 2) {
            if(BinaryNode.getHeight(node.left.left) >= BinaryNode.getHeight(node.left.right)) {
                rotateLeft(node);
                updateNodeHeight(node.left);
                updateNodeHeight(node);
            }
            else {
                rotateRight(node.left);
                updateNodeHeight(node.left.left);
                rotateLeft(node);
                updateNodeHeight(node.right.right);
            }
        }
    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    /* Toutes les opérations sont O(1) et il n'y a aucune boucle dans la méthode. La méthode possède donc
    une complexité qui est O(1).
    * */
    private void rotateLeft (BinaryNode < ValueType > node1) {
        BinaryNode<ValueType> parentOfNode = node1.parent;
        boolean isRightChild = parentOfNode != null && parentOfNode.right.value == node1.value;
        BinaryNode<ValueType> tmp = node1.left;
        node1.left = tmp.right;
        tmp.right = node1;
        node1.parent = tmp;
        if (node1.left != null) node1.left.parent = node1;
        tmp.parent = parentOfNode;
        if (parentOfNode == null) root = tmp;
        else if (isRightChild) parentOfNode.right = tmp;
        else parentOfNode.left = tmp;
    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    /* Toutes les opérations sont O(1) et il n'y a aucune boucle dans la méthode. La méthode possède donc
    une complexité qui est O(1).
    */
    private void rotateRight (BinaryNode < ValueType > node1) {
        BinaryNode<ValueType> parentOfNode = node1.parent;
        boolean isLeftChild = parentOfNode != null && parentOfNode.left.value == node1.value;
        BinaryNode<ValueType> tmp = node1.right;
        node1.right = tmp.left;
        tmp.left = node1;
        node1.parent = tmp;
        if (node1.right != null) node1.right.parent = node1;
        tmp.parent = parentOfNode;
        if (parentOfNode == null) root = tmp;
        else if (isLeftChild) parentOfNode.left = tmp;
        else parentOfNode.right = tmp;
    }

    /** TODO O( n )
     *  TODO: What's the average case? Briefly explain
     * Builds items which should contain all values within the root tree in ascending order
     * @param currentNode Node currently being accessed in this recursive method
     * @param items List being modified to contain all values in the root tree in ascending order
     */
    /*
     Cette méthode est une fonction récursive qui permet d'ajouter les éléments de notre avl dans une liste.
     Alors, on parcourt tous les noeuds de notre Avl. La complexité moyenne est donc : O(1+1+2n+log(n))=> O(n)
     */
    private void infixOrder (BinaryNode < ValueType > currentNode, List < ValueType > items){
        if (currentNode == null)
            return;
        infixOrder(currentNode.left, items);
        items.add(currentNode.value);
        infixOrder(currentNode.right, items);
    }

    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        // Null-safe height accessor
        // Raw use of parameterized class is justified because we do not use any value of the BinaryNode, only the height of the parameter
        static public int getHeight(BinaryNode node) {
            return node != null ? node.height : -1;
        }

        BinaryNode(ValueType value) {
            this.value = value;
        }
    }
}
