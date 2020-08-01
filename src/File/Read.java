package File;

import Graph.Graph;
import Graph.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

    private static String name(String line) {
        return line.split(" ")[1];
    }

    private static int number(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }

    private static Edge edge(String line) {
        String[] s = line.split(" ");
        return new Edge(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
    }

    public static Graph fileToGraph(String filename) throws Exception {
        try {
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
                    numberOfNodes = number(line);
                }
                else if (line.contains("E")) {
                    edges.add(edge(line));
                }
                else if (line.contains("T")) {
                    terminals.add(number(line));
                }
            }
            if (name == "" || numberOfNodes == 0) {
                throw new Exception("Wrong file format.");
            }
            Graph graph = new Graph(name, numberOfNodes, edges, terminals);
            reader.close();
            return graph;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
