import Graph.Graph;
import Steiner.MinimumSpanningTree;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4 , 5, 6);


        System.out.println(g);
        System.out.println(g.getSortedEdges());
        System.out.println(MinimumSpanningTree.MST(g));
    }
}
