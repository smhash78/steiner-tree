package Graph;

import Graph.Steiner.EdgeStatus;
import Graph.Steiner.Union;

import java.util.ArrayList;
import java.util.Stack;

public class Graph {
    private String name;
    private ArrayList<Node> nodes;

    // Constructors:
    public Graph() {
        name = "";
    }

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
        name = "";
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

    public void addNode(Node node) {
        if (nodes == null) {
            nodes = new ArrayList<Node>();
        }
        if (nodes.contains(node))
            nodes.remove(node);
        nodes.add(node);
    }

    public void addEdge(int a, int b, int value) {
        if (!nodesIndices().contains(a) || !nodesIndices().contains(b))
            return;
        Edge newEdge = new Edge(getNodeByIndex(a), getNodeByIndex(b), value);
        addEdgeToNodes(newEdge);
    }

    public boolean hasNode(Node node) {
        if (nodes == null)
            return false;
        return nodesIndices().contains(node.getIndex());
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


        return new Graph(nodes.size(), seenEdges, getTerminalsArray());
    }

    public Graph steiner() throws Exception {
        // For all edges , the method removes edge.
        // The tree will be divided into two subtrees.
        // The subtrees are retrieved using DFS and stack of nodes.
        // If one of the subtrees contains zero terminals, the algorithm will be continued with the other one;
        // Otherwise the removed edge will turn back to the tree.
        // The algorithm continues until it passes all of the edges.

        ArrayList<Edge> sortedEdges = edges();
        // Collections.reverse(sortedEdges);
        // Sorting the edges will cost 20 times more time for the same result!
        if (sortedEdges.size() == 0) {
            return this;
        }
        Graph aTree, bTree;
        Edge currentEdge;
        int i;
        for (i = 0; i < sortedEdges.size(); i++) {
            currentEdge = sortedEdges.get(i);
            removeEdge(currentEdge);
            aTree = DFS(getNodeByIndex(currentEdge.getA().getIndex()));
            bTree = DFS(getNodeByIndex(currentEdge.getB().getIndex()));

            if (aTree.getTerminals().size() == 0) {
                return bTree.steiner();
            }
            if (bTree.getTerminals().size() == 0) {
                return aTree.steiner();
            }
            addEdgeToNodes(currentEdge);
        }
        return this;
    }

    public void removeEdge(Edge edge) {
        int i;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getEdges().contains(edge))
                nodes.get(i).removeEdge(edge);
        }
    }

    public Graph DFS(Node node) {
        Graph dfsGraph = new Graph();
        Stack<Node> s = new Stack<Node>();
        s.push(node);
        int i;
        while (!s.isEmpty()) {
            Node n = s.pop();
            if (!(dfsGraph.hasNode(n))) {
                dfsGraph.addNode(n);
                for (i = 0; i < n.getEdges().size(); i++) {
                    if (!dfsGraph.hasNode(n.getEdges().get(i).getA())) {
                        s.push(getNodeByIndex(n.getEdges().get(i).getA().getIndex()));
                    }
                    else if (!dfsGraph.hasNode(n.getEdges().get(i).getB())) {
                        s.push(getNodeByIndex(n.getEdges().get(i).getB().getIndex()));
                    }
                }
            }
        }

        return dfsGraph;
    }

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

    public ArrayList<Integer> getTerminals() {
        ArrayList<Integer> terminals = new ArrayList<>();
        int i;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).isTerminal())
                terminals.add(nodes.get(i).getIndex());
        }
        return terminals;
    }

    public int[] getTerminalsArray() {
        ArrayList<Integer> terminals = getTerminals();
        int[] terminalsArray = new int[terminals.size()];
        int i;
        for (i = 0; i < terminals.size(); i++) {
            terminalsArray[i] = terminals.get(i);
        }
        return terminalsArray;
    }

    public void setTerminals(ArrayList<Integer> terminals) throws Exception {
        int i;
        for (i = 0; i < terminals.size(); i++) {
            if (terminals.get(i) < 0)
                throw new Exception(name + ") terminals[" + i + "] = " + terminals.get(i) + " is not in nodes.");
            getNodeByIndex(terminals.get(i)).setTerminal(true);
        }
    }

    public void setTerminals(int[] terminals) throws Exception {
        int i;
        for (i = 0; i < terminals.length; i++) {
            if (terminals[i] < 0)
                throw new Exception(name + ") terminals[" + i + "] = " + terminals[i] + " is not in nodes.");
            getNodeByIndex(terminals[i]).setTerminal(true);
        }
    }

    public String showNodes() {
        String res = "[";
        int i;
        for (i = 0; i < nodes.size(); i++) {
            res += nodes.get(i) + ", ";
        }
        return res + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

}
