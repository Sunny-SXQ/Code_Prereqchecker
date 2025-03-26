package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
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
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
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

    int v = Integer.parseInt(list.get(0)); //use string double arraylist(adj) to set adjlist graph
    ArrayList<ArrayList<String>> adj = new ArrayList<ArrayList<String>>(v);
    for (int i=0; i<v; i++) {
        adj.add(new ArrayList<String>());
        adj.get(i).add(list.get(i+1)); //use for loop to create all vertices
    }
    for (int i=v+2; i<list.size(); i=i+2) {
        Graph.addEdge(adj, list.get(i), list.get(i+1)); //use graph class to add Edge
    }

    ArrayList<String> list1 = new ArrayList<String>(); //write eligible.in file into string arraylist(list1)
    StdIn.setFile(args[1]);
    while (StdIn.hasNextLine()) {
        while (StdIn.hasNextChar()) {
            String s = StdIn.readString();
            list1.add(s);
        }
    }

    ArrayList<String> marked = new ArrayList<>();//use DFS method to put all finished courses into arraylist marked
    for (int j=1; j<list1.size(); j++) {
        marked = DFS(adj, marked, list1.get(j));
    }

    ArrayList<String> eligible = new ArrayList<>();//use for loop to put eligible courses into arraylist eligible
    for (int k=0; k<adj.size(); k++) {
        if (adj.get(k).size()==1) {
            eligible.add(adj.get(k).get(0));
        }
        else {
            for (int l=1; l<adj.get(k).size(); l++) {
                if (!marked.contains(adj.get(k).get(l))) {
                    break;
                }
                else {
                    if (l==adj.get(k).size()-1) {
                    eligible.add(adj.get(k).get(0));
                    }
                }
            }
        }
    }
    StdOut.setFile(args[2]);
    for (int m=0; m<eligible.size(); m++) {
        if (!marked.contains(eligible.get(m))) {
            StdOut.println(eligible.get(m));
        }
    }
    }
}

