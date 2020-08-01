package Steiner;

import Graph.Graph;
import Graph.Edge;

import java.util.ArrayList;

public class MinimumSpanningTree {

    public static Graph MST(Graph graph) {
        // This method returns the MST of a graph.
        int[] parents = graph.getNodes();
        int[] sizes = new int[graph.getNodes().length];
        int i;
        for (i = 0; i < sizes.length; i++)
            sizes[i] = 1;

        // 1- Sorting the edges by their values.
        ArrayList<Edge> sortedEdges = graph.getSortedEdges();
        ArrayList<Edge> seenEdges = new ArrayList<Edge>();

        // 2- Union-By-Size
        boolean done = false;
        Union u = new Union(graph.getNodes().length);
        for (i = 0; i < sortedEdges.size() && !done; i++) {
            done = u.unionBySize(u, sortedEdges.get(i).getA(), sortedEdges.get(i).getB());
        }

        // 3- Making the MST
        return null;
    }
}
