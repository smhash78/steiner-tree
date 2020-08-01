package Graph;

import java.util.ArrayList;

public class Graph {
    private String name;
    private int[] nodes;
    private ArrayList<Edge> edges;

    public Graph(int numberOfNodes) {
        nodes = new int[numberOfNodes];
        edges = new ArrayList<Edge>();

        int i;
        for (i = 0; i < numberOfNodes; i++)
            nodes[i] = i;
    }

    public Graph(int numberOfNodes, ArrayList<Edge> edges) {
        this(numberOfNodes);
        this.edges = edges;
    }

    public Graph(int numberOfNodes, int[] as, int[] bs, int[] values) {

        if (as.length != bs.length)
            return;
        nodes = new int[numberOfNodes];

        int i;
        for (i = 0; i < numberOfNodes; i++)
            nodes[i] = i;

        for (i = 0; i < as.length; i++) {
            addEdge(as[i], bs[i], values[i]);
        }
    }

    @Override
    public String toString() {
        return edges.toString();
    }

    public void addEdge(int a, int b, int value) {
        if (a > nodes.length || b > nodes.length)
            return;
        Edge newEdge = new Edge(a, b, value);
        edges.add(newEdge);
    }

    public ArrayList<Edge> getSortedEdges() {
        ArrayList<Edge> sortedEdges = edges;
        sortedEdges.sort(new SortEdgesByValue());
        return sortedEdges;
    }

    public int getValue() {
        int res = 0, i;
        for (i = 0; i < edges.size(); i++)
            res += edges.get(i).getValue();
        return res;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
