package Alphabet;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<ValueType> {
    private HashMap<ValueType, Integer> vertexMap;
    ArrayList<Vertex<ValueType>> vertices;
    private Integer nbVertex;

    Graph() {
        nbVertex = 0;
        vertices = new ArrayList<>();
        vertexMap = new HashMap<>();
    }

    private Vertex<ValueType> createVertex(ValueType value) {
        Integer pos = vertexMap.get(value);

        if (pos == null) {
            pos = nbVertex;
            vertexMap.put(value, pos);
            vertices.add(new Vertex(value));
            nbVertex++;
        }

        return vertices.get(pos);
    }

    /**
     * Creates an oriented edge between two vertex
     * @param src Vertex from which the oriented edge starts
     * @param dst Vertex to which the oriented edge points
     *
     * If the Vertex src or dst are not present in the graph they will be created
     * If an edge already exists between these vertex it will not add a second one
     */
    public void connect(ValueType src, ValueType dst) {
        //Get vertex or create them if they dont exists
        Vertex<ValueType> srcVertex = createVertex(src);
        Vertex<ValueType> dstVertex = createVertex(dst);

        //Connect vertex
        srcVertex.connect(dstVertex);
    }
}
