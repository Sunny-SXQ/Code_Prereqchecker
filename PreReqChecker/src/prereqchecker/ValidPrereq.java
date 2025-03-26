package prereqchecker;
import java.util.ArrayList;
/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */

public class ValidPrereq {
    
    public static ArrayList<String> DFS (ArrayList<ArrayList<String>> adj, ArrayList<String> visited, String s) {
        visited.add(s);
        dfs(adj,visited,s);//call dfs method to put all finished course into arraylist(visited)
        return visited;
    }
    public static void dfs (ArrayList<ArrayList<String>> adj, ArrayList<String> visited, String s) {
        for (int i=0; i<adj.size();i++) {
            if (adj.get(i).get(0).equals(s)) {
                for (int j=0; j<adj.get(i).size();j++) {
                    if (!visited.contains(adj.get(i).get(j))) {
                        visited.add(adj.get(i).get(j));
                        dfs(adj, visited, adj.get(i).get(j));//recursive to traverse all unvisited vertices,put it into arraylist(visited)
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE
    ArrayList<String> list = new ArrayList<String>();//write adjlist.in file into string arraylist(list)
    StdIn.setFile(args[0]);
    while (StdIn.hasNextLine()) {
        while (StdIn.hasNextChar()) {
            String s = StdIn.readString();
            list.add(s);
        }
    }
    int v = Integer.parseInt(list.get(0));//use string double arraylist(adj) to set adjlist graph
    ArrayList<ArrayList<String>> adj = new ArrayList<ArrayList<String>>(v);
    for (int i=0; i<v; i++) {
        adj.add(new ArrayList<String>());
        adj.get(i).add(list.get(i+1));//use for loop to create all vertices
    }
    for (int i=v+2; i<list.size(); i=i+2) {
        Graph.addEdge(adj, list.get(i), list.get(i+1));//use graph class to add Edge
    }
    ArrayList<String> list1 = new ArrayList<String>();//write validprereq.in file into string arraylist(list1)
    StdIn.setFile(args[1]);
    while (StdIn.hasNextLine()) {
        while (StdIn.hasNextChar()) {
            String s = StdIn.readString();
            list1.add(s);
        }
    }
    String course1 = list1.get(0);
    String course2 = list1.get(1);
    ArrayList<String> marked = new ArrayList<String>();
    marked=DFS(adj, marked, course2);//use DFS method to traverse all vertices from vertax-course2, put all vertices into arraylist(marked)
    
    StdOut.setFile(args[2]);
    if (!marked.contains(course1)){//check if there is vertax-course1 in arraylist(marked)
        StdOut.print("YES");
    }
    else {StdOut.print("NO");}  
    }
}
