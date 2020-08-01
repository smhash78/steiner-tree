import Graph.Graph;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, 5);
        g.addEdge(2, 0, -2);


        System.out.println(g.getSortedEdges());
    }
}
