package Steiner;

public class Union {
    private int[] parents;
    private int[] sizes;

    public Union(int length) {
        parents = new int[length];
        sizes = new int[length];
    }

    public Union(int[] parents, int[] sizes) {
        this.parents = parents;
        this.sizes = sizes;
    }

    private int find(int x) {
        int parent = parents[x];
        if (parent == x)
            return parent;
        return parents[x] = find(parent);
    }

    public EdgeStatus unionBySize(Union u, int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot)
            return EdgeStatus.notSeen;
        if (sizes[aRoot] < sizes[bRoot]) { // Swap
            int temp = aRoot;
            aRoot = bRoot;
            bRoot = temp;
        }
        parents[bRoot] = aRoot;
        sizes[aRoot] += sizes[bRoot];
        if (sizes[aRoot] == parents.length) // done
            return EdgeStatus.last;
        return EdgeStatus.middle;
    }
}
