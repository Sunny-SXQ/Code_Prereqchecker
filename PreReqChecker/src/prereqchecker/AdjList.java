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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE
    ArrayList<String> list = new ArrayList<String>();//write adjlist.in file into string arraylist(list)
    StdIn.setFile(args[0]);
    while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            list.add(s);
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

    StdOut.setFile(args[1]);
    for (int i=0; i<adj.size(); i++) {
        for (int j=0; j<adj.get(i).size();j++) {
            StdOut.print(adj.get(i).get(j)+" ");//print all adjlist for one vertax
        }
        StdOut.println();//use new line to print adjlist for next vertax
    }
    }

    private static boolean isEmpty() {
        return false;
    }
}

