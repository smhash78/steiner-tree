package Graph;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private int index;
    private boolean isTerminal;
    private ArrayList<Edge> edges;


    public Node(int index, boolean isTerminal) {
        this.index = index;
        this.isTerminal = isTerminal;
        edges = new ArrayList<Edge>();
    }

    @Override
    public String toString() {
        return (isTerminal?"T":"") + index + edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return index == node.index;
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
