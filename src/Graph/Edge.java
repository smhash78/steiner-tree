package Graph;

public class Edge {
    private int a;
    private int b;
    private int value;

    public Edge(int a, int b, int value) {
        this.a = a;
        this.b = b;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + value + ")";
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
