package Graph;

import java.util.Comparator;

public class SortEdgesByValue implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.getValue() - o2.getValue();
    }
}
