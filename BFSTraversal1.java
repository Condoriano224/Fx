package ch30;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTraversal1 {
	private int node;       /* total number of nodes in the graph */  
    private LinkedList<Integer> adj[];      /* adjacency list */  
    private Queue<Integer> que;           /* maintaining a queue */  
    
    @SuppressWarnings("unchecked")
	BFSTraversal1(int v) {  
        node = v;  
        adj = new LinkedList[node];  
        for (int i = 0; i < v; i++) {  
            adj[i] = new LinkedList<>();  
        }  
        que = new LinkedList<Integer>();  
    }  
    
    // Method to insert an undirected edge
    void insertEdge(int u, int v) {  
        adj[u].add(v);      /* adding an edge from v to w */  
        adj[v].add(u);      /* adding an edge from w to v */  
    }  
    
    void BFS(int n) {  
        boolean nodes[] = new boolean[node];       /* initialize boolean array for holding the data */  
        nodes[n] = true;                    
        que.add(n);       /* root node is added to the top of the queue */  
        
        while (que.size() != 0) {  
            n = que.poll();        /* remove the top element of the queue */   
            System.out.print(n + " ");    /* print the top element of the queue */  
            
            for (int i = 0; i < adj[n].size(); i++) {  /* iterate through the linked list and push all neighbors into queue */  
                int a = adj[n].get(i);  
                if (!nodes[a]) {      /* only insert nodes into queue if they have not been explored already */  
                    nodes[a] = true;  
                    que.add(a);  
                }  
            }    
        }  
    }  
    
    public static void main(String args[]) {  
        BFSTraversal1 graph = new BFSTraversal1(6);  
        graph.insertEdge(0, 1);  
        graph.insertEdge(0, 3);  
        graph.insertEdge(0, 4); 
        /*graph.insertEdge(1, 2);  
        graph.insertEdge(3, 5);  
        graph.insertEdge(4, 5); */
        graph.insertEdge(4, 5);  
        graph.insertEdge(3, 5);  
        graph.insertEdge(1, 2);  
        
        System.out.println("Breadth First Traversal for the graph is:");  
        graph.BFS(0);  
    }

}
