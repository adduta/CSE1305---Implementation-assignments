package Week8.prerequisites;

import java.util.*;

public class Cycles {

    /**
     * Detects cycles in a connected component.
     *
     * @param s starting vertex in our connected component.
     * @param vertices the vertex that belong to our graph.
     * @return true iff there is a cycle in the connected component the source belongs to.
     */
    public static boolean detectCycle(Vertex s, List<Vertex> vertices) {
        
    }

}

class VertexCycles {

    List<VertexCycles> outgoingEdges;

    int id;

    public VertexCycles(int id) {
        this.outgoingEdges = new ArrayList<>();
        this.id = id;
    }

    public List<VertexCycles> getOutgoingEdges() {
        return outgoingEdges;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return Integer.toString(id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
