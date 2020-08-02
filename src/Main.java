import Graph.Graph;

import static File.Read.fileToGraph;

public class Main {

    public static void main(String[] args) throws Exception {
        Graph g = fileToGraph("G:/Semester 4/Data Structure (Dr Keshtkaran)/Project/SteinerTree/src/file2.txt");



//
//        System.out.println(g);
        g = g.MST();
        //System.out.println(g.getNodes().get(1).getEdges());
        System.out.println(g.steiner());


    }
}
