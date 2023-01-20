import java.security.Key;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<KeyType, DataType>  {

    // DEFAULT_CAPACITY has to be a prime number
    private static final int DEFAULT_CAPACITY = 23;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size = 0;
    private int capacity;
    private final float loadFactor; // Compression factor

    public HashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /** TODO
     * Define capacity, loadFactor and map
     */
    public HashMap(int initialCapacity, float loadFactor) {
        this.loadFactor = Math.abs(loadFactor);
        if(initialCapacity <= 0)
            initialCapacity = DEFAULT_CAPACITY;
        this.capacity = nextPrime(initialCapacity);
        map = new Node[this.capacity];
    }

    /**
     * Finds the index attached to a particular key
     * This is the hashing function ("Fonction de dispersement")
     * @param key Value used to access to a particular instance of a DataType
     * @return Index value where this key should be placed in `map`
     */
    private int hash(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    /**
     * @return if it should be rehashed
     */
    private boolean needRehash() {
        return size > capacity * loadFactor;
    }

    /**
     * @return Number of elements
     */
    public int size() {
        return size;
    }

    /**
     * @return Current reserved space
     */
    public int capacity(){
        return capacity;
    }

    /**
     * @return if it is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // on a créé cette méthode pour tester si le nombre est premier ou non
    public boolean isPrime(int number) {
        int maxValue = (int) Math.sqrt(number);
        if(number==0 || number==1)
            return false;
        for (int i = 2; i <= maxValue; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /** TODO
     * Finds the next prime
     * @param number number to start the search to the next prime
     * @return Next closest prime
     */
    private int nextPrime(int number) {
        boolean isNotPrime = true;
        if (isPrime(number))
            return number;
        while(isNotPrime){
            number++;
            isNotPrime= !isPrime(number);
        }
        return number;
    }

    /** TODO Worst Case : O(m + n)
     * m = Capacity of the hashmap
     * n = number of elements in the hashmap
     * Increases capacity to the next prime number after capacity * CAPACITY_INCREASE_FACTOR and
     * reassigns all contained values
     */
    private void rehash() {
        Node<KeyType, DataType>[] oldMap = map;
        capacity = nextPrime(capacity*CAPACITY_INCREASE_FACTOR);
        map = new Node[capacity];
        int oldSize = size;
        size = 0;
        for(Node<KeyType, DataType> node : oldMap) {
            if (node != null) {
                for (Node<KeyType, DataType> n : node) {
                    put(n.key, n.data);
                }
            }
        }
        size = oldSize;
    }

    /** TODO Average Case : O(1)
     * Finds if the key is already assigned
     * @param key Key which we want to know if exists already
     * @return if key is already used
     */
    public boolean containsKey(KeyType key) {
        return get(key) != null;
    }

    /** TODO Worst Case : O(m + n)
     * m = Capacity of the hashmap
     * n = number of elements in the hashmap
     * Finds if the value is already present
     * @param value Value which we want to know if exists already
     * @return if value is already present
     */
    public boolean containsValue(DataType value) {
        for(Node<KeyType, DataType> item : map){
            if(item!=null) {
                for (Node<KeyType, DataType> items : item) {
                    if (items.data.equals(value))
                        return true;
                }
            }
        }
        return false;
    }

    /** TODO Average Case : O(1)
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        if (map[hash(key)]==null)
            return null;
        for (Node<KeyType, DataType> item: map[hash(key)]) {
            if (item.key.equals(key))
                return item.data;
        }
        return null;
    }

    /** TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        DataType data = get(key);
        if(!containsKey(key)){
            if(map[hash(key)] == null){
                map[hash(key)] = new Node<>(key, value);
                size++;
                if (needRehash())
                    rehash();
            }
            else{
                Node<KeyType, DataType> currentNode = map[hash(key)];
                while (currentNode.next != null){
                    currentNode = currentNode.next;
                }
                currentNode.next = new Node<>(key, value);
                size++;
            }
        }
        else
            map[hash(key)].data = value;
        return data;
    }

    /** TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key if it's absent
     * @param key Key which will have its value assigned if absent
     * @return Current DataType instance at key (null if absent)
     */
    public DataType putIfAbsent(KeyType key, DataType value) {
         if(get(key) == null) {
             put(key, value);
             return null;
         }
         return get(key);
    }

    /** TODO Average Case : O(1)
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
        DataType data = get(key);
        if(containsKey(key)) {
            if(map[hash(key)].key.equals(key))
                map[hash(key)] = map[hash(key)].next;
            else{
                Node<KeyType, DataType> currentNode = map[hash(key)];
                while(!currentNode.next.key.equals(key)){
                    currentNode = currentNode.next;
                }

                currentNode.next = currentNode.next.next;
            }
            size--;
        }
        return data;
    }

    /** TODO Worst Case : O(1)
     * Removes all nodes
     */
    public void clear() {
        map = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    static class Node<KeyType, DataType> implements Iterable<Node<KeyType, DataType>> {
        final KeyType key;
        DataType data;
        Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }

        @Override
        public Iterator<Node<KeyType, DataType>> iterator() {
            return new NodeIterator(this);
        }
        private class NodeIterator implements Iterator<Node<KeyType, DataType>> {
            private Node<KeyType, DataType> curr;

            /** TODO
             * Set curr to the current node
             */
            NodeIterator(Node<KeyType, DataType> node) {
                curr = node;
            }

            /** TODO
             * check if `curr` has a next element
             * @return if the current node has a next element
             */
            @Override
            public boolean hasNext() {
                return curr != null;
            }

            /** TODO
             * Defines `curr` to next element (null if none)
             * @return Old curr Node
             */
            @Override
            public Node<KeyType, DataType> next() {
                Node<KeyType, DataType> oldNode = curr;
                if(!hasNext())
                    throw new NoSuchElementException();
                curr = curr.next;
                return oldNode;
            }
        }
    }
}