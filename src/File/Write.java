package File;

import Graph.Graph;

import java.io.FileWriter;
import java.io.IOException;

public class Write {


    public static void graphToFile(Graph graph, String filename) throws IOException {
        //File file = new File("x.txt");
        FileWriter writer = new FileWriter("files/out/" + filename + ".out");
        writer.write("Cost " + graph.getValue() + "\n");
        writer.write("Edges " + graph.edges().size() + "\n");
        int i;
        for (i = 0; i < graph.edges().size(); i++) {
            writer.write("E " + (graph.edges().get(i).getA().getIndex() + 1)
                            + " " + (graph.edges().get(i).getB().getIndex() + 1) + "\n");
        }
        writer.close();

    }
}
