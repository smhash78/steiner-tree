package Graph;

public class Edge {
    private Node a;
    private Node b;
    private int value;

    public Edge(Node a, Node b, int value) {
        this.a = a;
        this.b = b;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + value + ")";
    }

    public Node getA() {
        return a;
    }

    public void setA(Node a) {
        this.a = a;
    }

    public Node getB() {
        return b;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
