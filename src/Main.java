import Graph.Graph;

import static File.Read.fileToGraph;
import static File.Write.graphToFile;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] filenames = {
                "bip42",
                "cc3-4",
                "cc3-5",
                "cc5-3",
                "cc6-2",
                "hc6",
                "hc7",
                "hc8",
                "hc9",
                "cc6-3",
                "cc3-10",
                "cc9-2"
        };

        Graph g;
        int i;
        for (i = 0; i < 12; i++) {
            g = fileToGraph(filenames[i] + "p");
            g = g.MST();
            System.out.println(filenames[i] + "p: " + g.getValue());
            graphToFile(g.steiner(),filenames[i] + "p");

            g = fileToGraph(filenames[i] + "u");
            g = g.MST();
            System.out.println(filenames[i] + "u: " + g.getValue());
            graphToFile(g.steiner(),filenames[i] + "u");
        }
    }
}
