package pt.ipp.isep.dei.esoft.project.MDISC;
import java.util.*;

class Edge1 implements Comparable<Edge1> {
    int src, dest, weight;

    public Edge1(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int compareTo(Edge1 compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Graph1 {
    int V, E;
    Edge1 edge[];

    Graph1(int v, int e) {
        V = v;
        E = e;
        edge = new Edge1[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge1(0, 0, 0);
    }

    int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    Edge1[] KruskalMST() {
        Edge1 result[] = new Edge1[V - 1];
        int e = 0;
        int i = 0;

        Arrays.sort(edge);

        int[] parent = new int[V + 1]; // Adicionando +1 ao tamanho
        Arrays.fill(parent, -1);

        i = 0;
        while (e < V - 1 && i < E) {
            Edge1 next_edge = edge[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
        }

        return result;
    }

    public void addEdge(int i, int src, int dest, int weight) {
        edge[i].src = src;
        edge[i].dest = dest;
        edge[i].weight = weight;
    }
}

public class US13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();

        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt();

        Graph1 graph = new Graph1(V, E);

        // Adding the edges
        for (int i = 0; i < E; i++) {
            System.out.println("Enter the details of edge " + (i + 1) + " (src dest weight):");
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(i, src, dest, weight);
        }

        // Executing Kruskal's algorithm
        Edge1[] minimumSpanningTree = graph.KruskalMST();

        // Displaying the minimum spanning tree
        System.out.println("Following are the edges in the constructed MST");
        int minimumCost = 0;
        for (Edge1 edge : minimumSpanningTree) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
            minimumCost += edge.weight;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minimumCost);

        scanner.close();
    }
}