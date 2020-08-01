package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Graph {
    private int[] nodes;
    private ArrayList<Edge> edges;

    public Graph(int numberOfNodes) {
        this.nodes = new int[numberOfNodes];
        this.edges = new ArrayList<Edge>();

        int i;
        for (i = 0; i < numberOfNodes; i++)
            this.nodes[i] = i;
    }

    public Graph(int numberOfNodes, int[] as, int[] bs, int[] values) {

        if (as.length != bs.length)
            return;
        this.nodes = new int[numberOfNodes];

        int i;
        for (i = 0; i < numberOfNodes; i++)
            this.nodes[i] = i;

        for (i = 0; i < as.length; i++) {
            this.addEdge(as[i], bs[i], values[i]);
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + Arrays.toString(nodes) +
                ", edges=" + edges +
                '}';
    }

    public void addEdge(int a, int b, int value) {
        if (a > this.nodes.length || b > this.nodes.length)
            return;
        Edge newEdge = new Edge(a, b, value);
        this.edges.add(newEdge);
    }

    public ArrayList<Edge> getSortedEdges() {
        ArrayList<Edge> sortedEdges = this.getEdges();
        sortedEdges.sort(new SortEdgesByValue());
        return sortedEdges;
    }

    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
