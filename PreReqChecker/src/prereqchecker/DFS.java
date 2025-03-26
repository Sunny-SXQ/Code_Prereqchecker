package prereqchecker;

import java.util.ArrayList;

public class DFS {
    private ArrayList<String> visited;

    public DFS (ArrayList<ArrayList<String>> adj, ArrayList<String> visited, String s) {
        //visited = new ArrayList<>();
        visited.add(s);
        dfs (adj,s);
    }
    private void dfs (ArrayList<ArrayList<String>> adj, String s) {
        for (int i=0; i<adj.size();i++) {
            if (adj.get(i).get(0).equals(s)) {
                for (int j=0; j<adj.get(i).size();j++) {
                    if (!visited.contains(adj.get(i).get(j))) {
                        visited.add(adj.get(i).get(j));
                        dfs(adj, adj.get(i).get(j));
                    }
                }
            }
        }
    }
    public ArrayList<String> visitedList(String s) {
        return visited;
    }
    
}
