public class UnionFind {

    private int count;
    private int[] id;
    private int[] rank;

    public UnionFind(int n) {
        count = n;
        id = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i)
            id[i] = i;
    }

    public int getCount() {
        return count;
    }

    private int find(int u) {
        return id[u] == u ? u : (id[u] = find(id[u]));
    }

    public void unionByRank(int u, int v) {
        final int i = find(u);
        final int j = find(v);
        if (i == j)
            return;
        if (rank[i] < rank[j]) {
            id[i] = id[j];
        } else if (rank[i] > rank[j]) {
            id[j] = id[i];
        } else {
            id[i] = id[j];
            ++rank[j];
        }
        --count;
    }

}
