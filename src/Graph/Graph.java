package Graph;

import Graph.Steiner.EdgeStatus;
import Graph.Steiner.Union;

import java.util.ArrayList;

public class Graph {
    private String name;
    private int[] nodes;
    private int[] terminals;
    private ArrayList<Edge> edges;

    // Constructors:
    public Graph(int numberOfNodes) {
        nodes = new int[numberOfNodes];
        edges = new ArrayList<Edge>();
        name = "";

        int i;
        for (i = 0; i < numberOfNodes; i++)
            nodes[i] = i;
    }

    public Graph(int numberOfNodes, ArrayList<Edge> edges) {
        this(numberOfNodes);
        this.edges = edges;
    }

    public Graph(String name, int numberOfNodes, ArrayList<Edge> edges, ArrayList<Integer> terminals) throws Exception {
        this(numberOfNodes, edges);
        this.name = name;
        setTerminals(terminals);
    }

    public Graph(int numberOfNodes, int[] as, int[] bs, int[] values) {
        name = "";
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

    public Graph(int numberOfNodes, int[] as, int[] bs, int[] values, int[] terminals) throws Exception {
        this(numberOfNodes, as, bs, values);
        setTerminals(terminals);
    }

    public Graph(String name, int numberOfNodes, int[] as, int[] bs, int[] values, int[] terminals) throws Exception {
        this(numberOfNodes, as, bs, values, terminals);
        this.name = name;
    }
    //

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

    public Graph MST() {
        // This method returns the MST of a graph.


        // 1- Sorting the edges by their values.
        ArrayList<Edge> sortedEdges = getSortedEdges();
        ArrayList<Edge> seenEdges = new ArrayList<Edge>();

        // 2- Union-By-Size
        // Initializing sizes array:
        // Initializing sizes array:
        int[] sizes = new int[nodes.length];
        int i;
        for (i = 0; i < sizes.length; i++)
            sizes[i] = 1;

        // The union object contains patterns array and sizes array (patterns array comes from graph.getNodes().)
        Union u = new Union(nodes, sizes);

        EdgeStatus status = EdgeStatus.notSeen; // To see if all nodes are seen.
        Edge currentEdge; // Used in for to make the code cleaner.
        // Union-By-Size iteration:

        for (i = 0; i < sortedEdges.size() && !(status == EdgeStatus.last); i++) {

            currentEdge = sortedEdges.get(i);
            status = u.unionBySize(currentEdge.getA(), currentEdge.getB());
            if (status != EdgeStatus.notSeen) {
                seenEdges.add(currentEdge);

            }
        }
        // 3- Making the MST and returning

        return new Graph(nodes.length, seenEdges);
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

    public int[] getTerminals() {
        return terminals;
    }

    public void setTerminals(int[] terminals) throws Exception {
        int i;
        for (i = 0; i < terminals.length; i++) {
            if (terminals[i] < 0 || terminals[i] >= nodes.length)
                throw new Exception(name + ") terminals[" + i + "] = " + terminals[i] + " is not in nodes.");
            this.terminals[i] = terminals[i];
        }
    }

    public void setTerminals(ArrayList<Integer> terminals) throws Exception {
        int i;
        for (i = 0; i < terminals.size(); i++) {
            if (terminals.get(i) < 0 || terminals.get(i) >= nodes.length)
                throw new Exception(name + ") terminals[" + i + "] = " + terminals.get(i) + " is not in nodes.");
            this.terminals[i] = terminals.get(i);
        }
    }

    // TODO
    public String showNodes() {
        String res = "[";
        int i;
        for (i = 0; i < nodes.length; i++) {
            res += Integer.toString(nodes[i]) + ", ";
        }
        return res + "]";
    }




}
