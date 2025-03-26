package prereqchecker;

import java.util.*;

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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
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
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
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

    ArrayList<String> list1 = new ArrayList<String>(); //write needtotake.in file into string arraylist(list1)
    StdIn.setFile(args[1]);
    while (StdIn.hasNextLine()) {
        while (StdIn.hasNextChar()) {
            String s = StdIn.readString();
            list1.add(s);
        }
    }

    String target = list1.get(0);
    ArrayList<String> marked = new ArrayList<String>();
    marked=DFS(adj, marked, target);//use DFS method to traverse all vertices from vertax-target, put all vertices into arraylist(marked)
    ArrayList<String> prereq = new ArrayList<>();
    for (int i=1; i<marked.size(); i++) {
        prereq.add(marked.get(i));//remove target from marked and create prereq list
    }

    ArrayList<String> needtotake = new ArrayList<>();

    if (list1.size()<3) {
        needtotake = prereq; //no course finished
    }

    else {
        ArrayList<String> finish = new ArrayList<>();//use DFS method to put all finished courses into arraylist(finish)
        for (int j=2; j<list1.size(); j++) {
            finish = DFS(adj, finish, list1.get(j));
        }
        for (int k=0; k<prereq.size(); k++) {
            if (!finish.contains(prereq.get(k))) {
                needtotake.add(prereq.get(k));//needtotake=prereq-finished
            }
        }
       /*for (int i=0; i<finish.size(); i++) {
           StdOut.print(finish.get(i)+" ");
       }*/
    }


    StdOut.setFile(args[2]);
    for (int m=0; m<needtotake.size(); m++) {
        StdOut.println(needtotake.get(m));
    }
    }
}
