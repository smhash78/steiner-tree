import Graph.Graph;

import static File.Read.fileToGraph;
import static File.Write.graphToFile;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] filenames = {
                "bipe2",
                "cc3-4",
                "cc3-5",
                "cc3-10",
                "cc5-3",
                "cc6-2",
                "cc6-3",
                "cc9-2",
                "hc6",
                "hc7",
                "hc8",
                "hc9"
        };

        long start = System.currentTimeMillis();
        Graph g;
        int i;
        for (i = 0; i < filenames.length; i++) {
            g = fileToGraph(filenames[i] + "p");
            g = g.MST();
            System.out.println(filenames[i] + "p: " + g.getValue());
            graphToFile(g.steiner(),filenames[i] + "p");

            g = fileToGraph(filenames[i] + "u");
            g = g.MST();
            System.out.println(filenames[i] + "u: " + g.getValue());
            graphToFile(g.steiner(),filenames[i] + "u");
        }
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time(s): " + (end - start)/1000.0);
    }
}
