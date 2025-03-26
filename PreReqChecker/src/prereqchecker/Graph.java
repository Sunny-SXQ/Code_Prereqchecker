package prereqchecker;

import java.util.ArrayList;

public class Graph {
    private final int V; //total number of vertax
    private ArrayList<ArrayList<String>> adj; //adjlist for all vertices
    
    public Graph(int V) {
        this.V = V;
        ArrayList<ArrayList<String>> adj = new ArrayList<ArrayList<String>>(V);
        for (int i=0; i<V; i++) {
            adj.add(new ArrayList<String>());
        }
    }

    public static void addEdge (ArrayList<ArrayList<String>> adj, String v, String w) { //add direct edge from vertax v to vertax w
        for (int i=0; i<adj.size();i++) {
            if (adj.get(i).get(0).equals(v)) {
                adj.get(i).add(w);
            }
        }
    }

    public static void printGraph (ArrayList<ArrayList<String>> adj) {
        for (int i=0; i<adj.size(); i++) {
            for (int j=0; j<adj.get(i).size();j++) {
                StdOut.print(adj.get(i).get(j)+" ");//print all adjlist for one vertax
            }
            StdOut.println();//use new line to print adjlist for next vertax
        }
    }
    
}

