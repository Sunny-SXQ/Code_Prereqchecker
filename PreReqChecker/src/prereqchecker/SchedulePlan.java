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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {

    public static void bfs (ArrayList<ArrayList<String>> adj, ArrayList<ArrayList<String>> visited, Integer a) {
        if (visited.get(a).size()==0) {return;}//return if the size of last index of visited is Zero.
        visited.add(new ArrayList<>());
        for (int i=0; i<visited.get(a).size(); i++) {
            for (int j=0; j<adj.size(); j++) {
                if (adj.get(j).get(0).equals(visited.get(a).get(i))){
                    if (adj.get(j).size()==1) {break;}
                    else {
                        for (int k=1; k<adj.get(j).size(); k++) {
                            visited.get(a+1).add(adj.get(j).get(k));
                        }
                    } 
                }
            }
        }
        bfs(adj, visited, a+1);
    }

    public static void dfs (ArrayList<ArrayList<String>> adj, ArrayList<String> visited, String s) {
        for (int i=0; i<adj.size();i++) {
            if (adj.get(i).get(0).equals(s)) {
                for (int j=0; j<adj.get(i).size();j++) {
                    if (!visited.contains(adj.get(i).get(j))) {
                        visited.add(adj.get(i).get(j));
                        dfs(adj, visited, adj.get(i).get(j));//recursive to traverse all finished courses,put it into arraylist-finish
                    }
                }
            }
        }
    }
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    ArrayList<String> list = new ArrayList<String>();//write adjlist.in file into string arraylist(list)
    StdIn.setFile(args[0]);
    while (!StdIn.isEmpty()) {
        String s = StdIn.readString();
        list.add(s);
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

    ArrayList<String> list1 = new ArrayList<String>(); //write scheduleplan.in file into string arraylist(list1)
    StdIn.setFile(args[1]);
    while (!StdIn.isEmpty()) {
        String s = StdIn.readString();
        list1.add(s);
    }

    String target = list1.get(0);
    ArrayList<ArrayList<String>> prereq = new ArrayList<ArrayList<String>>();
    prereq.add(new ArrayList<>());
    for (int i=0; i<adj.size(); i++) {
        if (adj.get(i).get(0).equals(target)) {
            for (int j=0; j<adj.get(i).size(); j++) {
                prereq.get(0).add(adj.get(i).get(j));//put adj courses of target course into the index 0 arraylist of prereq
            }
        }
    }
    prereq.get(0).remove(0);//rmove target course from prereq

    bfs (adj, prereq, 0);//use bfs method to create all prereq courses, put all courses into Arrayarraylist(prereq)
    
    ArrayList<String> finish = new ArrayList<>();//use dfs method to put all finished courses into arraylist(finish)
    for (int j=2; j<list1.size(); j++) {
        finish.add(list1.get(j));
        dfs(adj, finish, list1.get(j));
    }

    ArrayList<ArrayList<String>> newPrereq = new ArrayList<ArrayList<String>>();//remove finished course from prereq courses
    for (int i=0; i<prereq.size(); i++){
        newPrereq.add(new ArrayList<>());
        for (int j=0; j<prereq.get(i).size(); j++) {
            if (!finish.contains(prereq.get(i).get(j))) {
                newPrereq.get(i).add(prereq.get(i).get(j));
            }
        }
    }

    ArrayList<ArrayList<String>> finalPrereq = new ArrayList<ArrayList<String>>();//remove the empty Arraylist from newPrereq and get size()
    for (int i=0; i<newPrereq.size(); i++){
        if (newPrereq.get(i).size()!=0) {
            finalPrereq.add(new ArrayList<String>());
            for (int j=0; j<newPrereq.get(i).size(); j++) {
                finalPrereq.get(i).add(newPrereq.get(i).get(j));
            }
        }
    }
   
    StdOut.setFile(args[2]);
    StdOut.println(finalPrereq.size());//print how many semisters are needed to finish all prereq courses
    ArrayList<String> marked = new ArrayList<>();
    for (int i=finalPrereq.size()-1; i>=0; i--){
        for (int j=0; j<finalPrereq.get(i).size(); j++){
            if (!marked.contains(finalPrereq.get(i).get(j))){//check if the course has been printed(finished)
                StdOut.print(finalPrereq.get(i).get(j)+" ");
                marked.add(finalPrereq.get(i).get(j));//put all printed(finished) course into marked arraylist
            }
        }
        StdOut.println();
    }
    }
}
