package ch30;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DFSTraversal1 {
	private LinkedList<Integer> adj[]; /* adjacency list representation */
    private boolean visited[];

    /* Creation of the graph */
    @SuppressWarnings("unchecked")
	DFSTraversal1(int V) { /* 'V' is the number of vertices in the graph */
        adj = new LinkedList[V];
        visited = new boolean[V];

        for (int i = 0; i < V; i++)
            adj[i] = new LinkedList<Integer>();
    }

    /* Adding an edge to the graph */
    void insertEdge(int src, int dest) {
        adj[src].add(dest);
    }

    /* DFS using stack */
    void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");

                // Add all adjacent vertices to the stack
                Iterator<Integer> it = adj[vertex].listIterator();
                while (it.hasNext()) {
                    int n = it.next();
                    if (!visited[n]) {
                        stack.push(n);
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        DFSTraversal1 graph = new DFSTraversal1(8);

        graph.insertEdge(0, 1);
        graph.insertEdge(0, 3);
        graph.insertEdge(0, 2);
        graph.insertEdge(1, 3);
        graph.insertEdge(2, 4);
        graph.insertEdge(3, 5);
        graph.insertEdge(3, 6);
        graph.insertEdge(4, 7);
        graph.insertEdge(4, 5);
        graph.insertEdge(5, 2);
        

        System.out.println("Depth First Traversal for the graph is:");
        graph.DFS(0);
    }

}
