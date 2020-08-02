package Graph;

import java.util.ArrayList;

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
        return (isTerminal?"T":"") + index;
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
}
