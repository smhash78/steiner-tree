package Steiner;

import Graph.Graph;
import Graph.Edge;

import java.util.ArrayList;

public class MinimumSpanningTree {

    public static Graph MST(Graph graph) {
        // This method returns the MST of a graph.


        // 1- Sorting the edges by their values.
        ArrayList<Edge> sortedEdges = graph.getSortedEdges();
        ArrayList<Edge> seenEdges = new ArrayList<Edge>();

        // 2- Union-By-Size
        // Initializing sizes array:
        int[] sizes = new int[graph.getNodes().length];
        int i;
        for (i = 0; i < sizes.length; i++)
            sizes[i] = 1;
        // The union object contains patterns array and sizes array (patterns array comes from graph.getNodes().)
        Union u = new Union(graph.getNodes(), sizes);
        EdgeStatus status = EdgeStatus.notSeen; // To see if all nodes are seen.
        Edge currentEdge; // Used in for to make the code cleaner.
        // Union-By-Size iteration:
        for (i = 0; i < sortedEdges.size() && !(status == EdgeStatus.last); i++) {
            currentEdge = sortedEdges.get(i);
            status = u.unionBySize(u, currentEdge.getA(), currentEdge.getB());
            if (status != EdgeStatus.notSeen)
                seenEdges.add(currentEdge);
        }

        // 3- Making the MST and returning
        return new Graph(graph.getNodes().length, seenEdges);
    }
}
