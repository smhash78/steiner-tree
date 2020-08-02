package Graph;

import Graph.Steiner.EdgeStatus;
import Graph.Steiner.Union;

import java.util.ArrayList;
import java.util.Stack;

public class Graph {
    private String name;
    private ArrayList<Node> nodes;
    private int[] terminals;

    // Constructors:
    public Graph(int numberOfNodes) {
        nodes = new ArrayList<Node>();
        name = "";

        int i;
        for (i = 0; i < numberOfNodes; i++) {
            Node n = new Node(i, false);
            n.setEdges(new ArrayList<Edge>());
            nodes.add(n);
        }
    }

    public Graph(int numberOfNodes, ArrayList<Edge> edges) {
        this(numberOfNodes);
        addEdgesToNodes(edges);
    }

    public Graph(int numberOfNodes, ArrayList<Edge> edges, int[] terminals) throws Exception {
        this(numberOfNodes);
        addEdgesToNodes(edges);
        setTerminals(terminals);
    }

    public Graph(String name, int numberOfNodes, ArrayList<Edge> edges, ArrayList<Integer> terminals) throws Exception {
        this(numberOfNodes, edges);
        this.name = name;
        setTerminals(terminals);
    }

    public Graph(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph(String name, ArrayList<Node> nodes) {
        this(nodes);
        this.name = name;
    }
    //

    public ArrayList<Edge> edges() {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        int i, j;
        for (i = 0; i < nodes.size(); i++) {
            for (j = 0; j < nodes.get(i).getEdges().size(); j++) {
                if (!edges.contains(nodes.get(i).getEdges().get(j))) {
                    edges.add(nodes.get(i).getEdges().get(j));
                }
            }
        }
        return edges;
    }

    @Override
    public String toString() {
        return edges().toString();
    }

    public void addEdge(int a, int b, int value) {
        if (!nodesIndices().contains(a) || !nodesIndices().contains(b))
            return;
        Edge newEdge = new Edge(getNodeByIndex(a), getNodeByIndex(b), value);
        newEdge.getA().addEdge(newEdge);
        newEdge.getB().addEdge(newEdge);
    }

    public ArrayList<Edge> getSortedEdges() {
        ArrayList<Edge> sortedEdges = edges();
        sortedEdges.sort(new SortEdgesByValue());
        return sortedEdges;
    }

    public int getValue() {
        int res = 0, i;
        ArrayList<Edge> edges = edges();
        for (i = 0; i < edges.size(); i++)
            res += edges.get(i).getValue();
        return res;
    }

    public Graph MST() throws Exception {
        // This method returns the MST of a graph.


        // 1- Sorting the edges by their values.
        ArrayList<Edge> sortedEdges = getSortedEdges();
        ArrayList<Edge> seenEdges = new ArrayList<Edge>();

        // 2- Union-By-Size
        // Initializing sizes array:
        // Initializing sizes array:
        int[] sizes = new int[nodes.size()];
        int i;
        for (i = 0; i < sizes.length; i++)
            sizes[i] = 1;

        // The union object contains patterns array and sizes array (patterns array comes from graph.getNodes().)
        int[] nodesIndices = new int[nodes.size()];
        for (i = 0; i < nodes.size(); i++) {
            nodesIndices[i] = nodes.get(i).getIndex();
        }
        Union u = new Union(nodesIndices, sizes);

        EdgeStatus status = EdgeStatus.notSeen; // To see if all nodes are seen.
        Edge currentEdge; // Used in for to make the code cleaner.
        // Union-By-Size iteration:

        for (i = 0; i < sortedEdges.size() && !(status == EdgeStatus.last); i++) {

            currentEdge = sortedEdges.get(i);
            status = u.unionBySize(currentEdge.getA().getIndex(), currentEdge.getB().getIndex());
            if (status != EdgeStatus.notSeen) {
                seenEdges.add(currentEdge);
            }
        }
        // 3- Making the MST and returning

        return new Graph(nodes.size(), seenEdges, this.terminals);
    }

//    public Graph steiner() throws Exception {
//        // This method uses the simplest (Exhaustive) way to make Steiner Tree.
//        // For all edges, it omits edge, and calculates the MST.
//        // If the terminals of new tree is equal to the graph, it continues,
//        // otherwise it turns back the omitted edge and continues.
//
//        Edge edge;
//        Graph result = this, aTree = null, bTree = null;
//        int i;
//        for (i = 0; i < result.edges.size(); i++) {
//            edge = result.edges.get(i);
//            result.edges.remove(edge);
//            removeEdgeFromNodes(edge);
//            aTree = makeSubGraphByNode(edge.getA());
//            bTree = makeSubGraphByNode(edge.getB());
//            if (aTree.getTerminals().length == 0)
//                result = bTree;
//            else if (bTree.getTerminals().length == 0)
//                result = aTree;
//        }
//        return result;
//    }

    public void removeEdgeFromNodes(Edge edge) {
        int i;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getEdges().contains(edge))
                nodes.get(i).getEdges().remove(edge);
        }
    }

//    public Graph DFS(Node node, Node[] seenNodes, ArrayList<Edge> seenEdges) throws Exception {
//        ArrayList<Integer> terminals = new ArrayList<>();
//        Stack<Node> s = new Stack<Node>();
//        s.push(node);
//        int i;
//        while (!s.isEmpty()) {
//            Node n = s.pop();
//            if (seenNodes[n.getIndex()] == null) {
//                seenNodes[n.getIndex()] = n;
//                if (n.isTerminal())
//                    terminals.add(n.getIndex());
//                for (i = 0; i < n.getEdges().size(); i++) {
//                    if (seenNodes[n.getEdges().get(i).getA().getIndex()] == null) {
//                        s.push(n.getEdges().get(i).getA());
//                    }
//                    else if (seenNodes[n.getEdges().get(i).getB().getIndex()] == null) {
//                        s.push(n.getEdges().get(i).getB());
//                    }
//                    seenEdges.add(n.getEdges().get(i));
//                }
//            }
//        }
//        int[] allTerminals = new int[terminals.size()];
//        for (i = 0; i < terminals.size(); i++) {
//            allTerminals[i] = terminals.get(i);
//        }
//        return new Graph(seenNodes.length,  seenEdges, allTerminals);
//    }
//
//    public Graph makeSubGraphByNode(Node node) throws Exception {
//        Node[] seenNodes = new Node[nodes.length];
//        ArrayList<Edge> seenEdges = new ArrayList<Edge>();
//        int i;
//        for (i = 0; i < nodes.length; i++) {
//            seenNodes[i] = null;
//        }
//        return DFS(node, seenNodes, seenEdges);
//    }


    public void addEdgeToNodes(Edge edge) {
        getNodeByIndex(edge.getA().getIndex()).addEdge(edge);
        getNodeByIndex(edge.getB().getIndex()).addEdge(edge);
    }


    public void addEdgesToNodes(ArrayList<Edge> edges) {
        int i;
        for (i = 0; i < edges.size(); i++) {
            addEdgeToNodes(edges.get(i));
        }
    }

    public ArrayList<Integer> nodesIndices() {
        ArrayList<Integer> indices = new ArrayList<>();
        int i;
        for (i = 0; i < nodes.size(); i++) {
            indices.add(nodes.get(i).getIndex());
        }
        return indices;
    }

    public Node getNodeByIndex(int index) {
        int i;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getIndex() == index)
                return nodes.get(i);
        }
        return null;
    }


    public void setTerminals(int[] terminals) throws Exception {
        this.terminals = new int[terminals.length];
        int i;
        for (i = 0; i < terminals.length; i++) {
            if (terminals[i] < 0)
                throw new Exception(name + ") terminals[" + i + "] = " + terminals[i] + " is not in nodes.");
            this.terminals[i] = terminals[i];
            getNodeByIndex(this.terminals[i]).setTerminal(true);
        }
    }

    public void setTerminals(ArrayList<Integer> terminals) throws Exception {
        this.terminals = new int[terminals.size()];
        int i;
        for (i = 0; i < terminals.size(); i++) {
            if (terminals.get(i) < 0)
                throw new Exception(name + ") terminals[" + i + "] = " + terminals.get(i) + " is not in nodes.");
            this.terminals[i] = terminals.get(i);
            getNodeByIndex(this.terminals[i]).setTerminal(true);
        }
    }


    // TODO
    public String showNodes() {
        String res = "[";
        int i;
        for (i = 0; i < nodes.size(); i++) {
            res += nodes.get(i) + ", ";
        }
        return res + "]";
    }

}
