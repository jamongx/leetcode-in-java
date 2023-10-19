public class UnionFind {

    private int count;
    private int[] id;
    private int[] rank;

    public UnionFind(int n) {
        this.count = n;
        this.id    = new int[n];
        this.rank  = new int[n];

        for (int i = 0; i < n; i++) {
            this.id[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    private int find(int u) {
        return this.id[u] == u ? u : (this.id[u] = find(id[u]));
    }

    public void unionByRank(int u, int v) {
        int i = find(u);
        int j = find(v);

        if (i == j) {
            return;
        }

        if (rank[i] < rank[j]) {
            id[i] = id[j];
        }
        else if (rank[i] > rank[j]) {
            id[j] = id[i];
        }
        else {
            id[i] = id[j];
            rank[j]++;
        }
        count--;
    }

}
