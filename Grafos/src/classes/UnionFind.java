package classes;

public class UnionFind {
    private int[] parent;
    
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int encontrar(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = encontrar(parent[x]);
    }
    
    public boolean saoConectados(int x, int y) {
        return encontrar(x) == encontrar(y);
    }
    
    public void unir(int x, int y) {
        int raizX = encontrar(x);
        int raizY = encontrar(y);
        if (raizX != raizY) {
            parent[raizX] = raizY;
        }
    }
}
