package File;

import Graph.Graph;
import Graph.Edge;
import Graph.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

    private static String name(String line) {
        return line.split(" ")[1];
    }

    private static int number(String line) {
        return Integer.parseInt(line.split(" ")[1]) - 1;
    }

    private static int numberOfNodes(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }

    private static Edge edge(String line) {
        String[] s = line.split(" ");
        Node a = new Node(Integer.parseInt(s[1]) - 1, false);
        Node b = new Node(Integer.parseInt(s[2]) - 1, false);
        return new Edge(a, b, Integer.parseInt(s[3]));
    }

    public static Graph fileToGraph(String filename) throws Exception {
        try {
            filename = "files/in/" + filename + ".stp";
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            String line, name = "";
            int numberOfNodes = 0;
            ArrayList<Integer> terminals = new ArrayList<>();
            ArrayList<Edge> edges = new ArrayList<Edge>();
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                if (line.contains("Name")) {
                    name = name(line);
                }
                else if (line.contains("Nodes")) {
                    numberOfNodes = numberOfNodes(line);
                }
                else if (line.contains("E ")) {
                    edges.add(edge(line));
                }
                else if (line.contains("T ")) {
                    terminals.add(number(line));
                }
            }
            if (name == "" || numberOfNodes == 0) {
                throw new Exception("Wrong file format.");
            }
            ArrayList<Node> nodes = new ArrayList<Node>();
            int i;
            for (i = 0; i < numberOfNodes; i++) {
                Node n = new Node(i, terminals.contains(i));
                nodes.add(n);
            }
            Graph graph = new Graph(name, nodes);
            graph.addEdgesToNodes(edges);
            reader.close();
            return graph;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
