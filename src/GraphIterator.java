import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Implements a Depth first traversal of the Graph, starting at constructor vertex v. It
 * should return nodes at most once. If there is a choice between multiple next nodes,
 * always pick the lowest id node.
 */
public class GraphIterator implements Iterator<Vertex> {

    private Graph g;

    private Vertex v;

    private Stack<Vertex> stack;

    private Set<Vertex> colored;

    private int graphSize;

    public GraphIterator(Graph g, Vertex v) {
        this.g = g;
        this.v = v;
        stack = new Stack<>();
        stack.push(v);
        colored = new TreeSet<>();
        graphSize = g.getAllVertices().size();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Vertex next() {
        Vertex result = null;
        if (!stack.isEmpty()) {
            result = stack.pop();

            if (!colored.contains(result)) {
                colored.add(result);

                ArrayList<Vertex> neighbours = (ArrayList<Vertex>) g.getNeighbours(result);

                for (int i = neighbours.size() - 1; i >= 0; i--) {
                    if (!colored.contains(neighbours.get(i))) {
                        stack.push(neighbours.get(i));
                    }
                }
            }
        }

        return result;
    }

}

interface Vertex extends Comparable<Vertex> {

    int getId();
}

/**
 * Interface for a generic graph. You may assume that our implementation is an
 * undirected graph.
 */
interface Graph {

    /**
     * Returns the neighbours in a sorted collection by id
     *
     * @param v
     *     node to get the neighbours of.
     * @return sorted collection of neighbours.
     */
    List<Vertex> getNeighbours(Vertex v);

    /**
     * @return an unsorted collection of all vertices in the graph.
     */
    Collection<Vertex> getAllVertices();
}

class VertexImpl implements Vertex {

    private int id;

    private Set<Vertex> neighbours;

    public VertexImpl(int id) {
        this.id = id;
        neighbours = new HashSet<>();
    }

    public void addNeighbour(Vertex v) {
        neighbours.add(v);
    }

    @Override
    public String toString() {
        return "<Vertex: " + getId() + ">";
    }

    @Override
    public int getId() {
        return id;
    }

    public Collection<Vertex> getNeighbours() {
        return new ArrayList<>(this.neighbours);
    }

    @Override
    public int compareTo(Vertex o) {
        return this.getId() - o.getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vertex) && (((Vertex) o).getId() == this.getId());
    }
}

class GraphImpl implements Graph {

    private Map<Integer, Vertex> vertices;

    public GraphImpl() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        this.vertices.put(v.getId(), v);
    }

    @Override
    public Collection<Vertex> getAllVertices() {
        return this.vertices.values();
    }

    @Override
    public List<Vertex> getNeighbours(Vertex v) {
        List<Vertex> neigh = new ArrayList<>(((VertexImpl) v).getNeighbours());
        Collections.sort(neigh);
        return neigh;
    }

    public void addEdge(Vertex v, Vertex w) {
        VertexImpl realV = (VertexImpl) v;
        VertexImpl realW = (VertexImpl) w;
        realV.addNeighbour(w);
        realW.addNeighbour(v);
    }
}

class TestSuiteGraphIterator {

    @org.junit.jupiter.api.Test
    public void testDoubleVertex() {
        GraphImpl g = new GraphImpl();
        Vertex v = new VertexImpl(0);
        Vertex w = new VertexImpl(1);
        g.addVertex(v);
        g.addVertex(w);
        g.addEdge(v, w);
        Iterator<Vertex> it = new GraphIterator(g, v);
        assertSame(v, it.next());
        assertTrue(it.hasNext());
        assertSame(w, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testSingleLoopingVertex() {
        GraphImpl g = new GraphImpl();
        Vertex v = new VertexImpl(0);
        g.addVertex(v);
        g.addEdge(v, v);
        Iterator<Vertex> it = new GraphIterator(g, v);
        assertTrue(it.hasNext());
        assertSame(v, it.next());
        assertFalse(it.hasNext());
    }
}
