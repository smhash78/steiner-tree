import Graph.Graph;

import static File.Read.fileToGraph;

public class Main {

    public static void main(String[] args) throws Exception {
        Graph g = fileToGraph("G:/Semester 4/Data Structure (Dr Keshtkaran)/Project/SteinerTree/src/file.txt");
        Graph h = fileToGraph("G:/Semester 4/Data Structure (Dr Keshtkaran)/Project/SteinerTree/src/file2.txt");


        //System.out.println(g);
        //System.out.println("1 " + g.showNodes());
        System.out.println(g.MST());
        //System.out.println("2 " + h.showNodes());
        //System.out.println(g);
        System.out.println(h.MST());
    }
}
