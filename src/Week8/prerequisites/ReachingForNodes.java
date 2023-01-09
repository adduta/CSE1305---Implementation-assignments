package Week8.prerequisites;

import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReachingForNodes {
    /**
     * Checks if vertex b is reachable from vertex a.
     * 89 / 100
     *
     * @param a
     *     Vertex to start from.
     * @param b
     *     Vertex to reach.
     * @return true if vertex b is reachable.
     */
    public static boolean solve(Vertex a, Vertex b) {
        if (a == null || b == null) return false;
        Queue<Vertex> vertices = new LinkedList<>();
        Map<Integer, Boolean> visited = new TreeMap<>();

        vertices.add(a);
        visited.put(a.getId(), true);
        while (!vertices.isEmpty()) {
            Vertex current = vertices.poll();

            for (Vertex neighbour : current.getNeighbours()) {
                if (visited.get(neighbour.getId()) == null) {
                    if (neighbour.equals(b)) return true;
                    visited.put(neighbour.getId(), true);
                    vertices.add(neighbour);
                }
            }

        }

        return false;
    }
}

class Vertex {

    private int id;

    private Set<Vertex> neighbours;

    public Vertex(int id) {
        this.id = id;
        neighbours = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void addNeighbour(Vertex v) {
        neighbours.add(v);
    }

    @Override
    public String toString() {
        return "<vertex: " + id + ">";
    }

    public Collection<Vertex> getNeighbours() {
        return new ArrayList<>(this.neighbours);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vertex && ((Vertex) o).getId() == this.getId();
    }
}

class Graph {

    private Map<Integer, Vertex> vertices = new HashMap<>();

    /**
     * Creates a new graph with n vertices.
     *
     * @param n
     *     Amount of vertices in the graph.
     */
    public Graph(int n) {
        for (int i = 0; i < n; i++) vertices.put(i, new Vertex(i));
    }

    /**
     * Returns the vertex with the given ID.
     *
     * @param id
     *     The ID for the vertex to get.
     * @return The vertex with the given ID.
     * @throws IllegalArgumentException
     *     if no vertex with the given ID is in the graph.
     */
    public Vertex getVertex(int id) throws IllegalArgumentException {
        if (!vertices.containsKey(id)) throw new IllegalArgumentException("Vertex not in graph");
        return vertices.get(id);
    }

    public Collection<Vertex> getAllVertices() {
        return new ArrayList<>(this.vertices.values());
    }

    /**
     * Adds an edge between vertex u and v with the given weight.
     *
     * @param u
     *     First vertex.
     * @param v
     *     Second vertex.
     */
    public void addEdge(Vertex u, Vertex v) {
        u.addNeighbour(v);
        v.addNeighbour(u);
    }

    /**
     * Adds an edge between the vertices with IDs u and v with the given weight.
     *
     * @param u
     *     ID of the first vertex.
     * @param v
     *     ID of the second vertex.
     * @throws IllegalArgumentException
     *     if no vertex with the given ID is in the graph.
     */
    public void addEdge(int u, int v) throws IllegalArgumentException {
        addEdge(getVertex(u), getVertex(v));
    }
}

class TestSuite1 {

    public boolean solve(Graph g, int a, int b) {
        return ReachingForNodes.solve(g.getVertex(a), g.getVertex(b));
    }

    /**
     * Tests the following graph
     * 0 - 1 - 2
     */
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void testLine() {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        // Path from 0 to 1 is reachable
        assertTrue(ReachingForNodes.solve(g.getVertex(0), g.getVertex(1)));
        // Path from 1 to 2 is reachable
        assertTrue(ReachingForNodes.solve(g.getVertex(1), g.getVertex(2)));
        // Path from 0 to 2 is reachable
        assertTrue(ReachingForNodes.solve(g.getVertex(0), g.getVertex(2)));
        // Path from 2 to 0 is reachable
        assertTrue(ReachingForNodes.solve(g.getVertex(2), g.getVertex(0)));
    }

    /**
     * Tests the following graph:
     * 0 - 1 - 3
     * |    \  |
     * |     \ |
     * 2  ---  4    (5)
     */
    @Test
    @Timeout(value = 450, unit = TimeUnit.MILLISECONDS)
    public void testWeighted() {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(1, 4);
        g.addEdge(0, 2);
        g.addEdge(2, 4);
        // Path is 0-1-4
        assertTrue(ReachingForNodes.solve(g.getVertex(0), g.getVertex(4)));
        // Path is 0-1-4-3
        assertTrue(ReachingForNodes.solve(g.getVertex(0), g.getVertex(3)));
        // Path is 2-4
        assertTrue(ReachingForNodes.solve(g.getVertex(2), g.getVertex(4)));
        // Path is 2-4-1-0
        assertTrue(ReachingForNodes.solve(g.getVertex(2), g.getVertex(0)));
        // No path
        assertFalse(ReachingForNodes.solve(g.getVertex(0), g.getVertex(5)));
        assertFalse(ReachingForNodes.solve(g.getVertex(1), g.getVertex(5)));
        assertFalse(ReachingForNodes.solve(g.getVertex(2), g.getVertex(5)));
        assertFalse(ReachingForNodes.solve(g.getVertex(3), g.getVertex(5)));
        assertFalse(ReachingForNodes.solve(g.getVertex(4), g.getVertex(5)));
    }
}
