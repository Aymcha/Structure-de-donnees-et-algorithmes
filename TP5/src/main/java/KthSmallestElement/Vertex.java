package KthSmallestElement;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>>{
    public int firstIndex;
    public int secondIndex;
    public T value;

    public Vertex(int first, int second, T value) {
        this.firstIndex = first;
        this.secondIndex = second;
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }

    @Override
    public int compareTo(Vertex<T> anotherPair) {
        return this.value.compareTo((T) anotherPair.getValue());
    }
}

