import Graph.Graph;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 5);

        System.out.println(g);
    }
}
