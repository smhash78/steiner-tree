package Steiner;

public class Union {
    private int[] parents;
    private int[] sizes;

    public Union(int length) {
        this.parents = new int[length];
        this.sizes = new int[length];
    }

    public boolean unionBySize(Union u, int a, int b) {
        return false;
    }
}
