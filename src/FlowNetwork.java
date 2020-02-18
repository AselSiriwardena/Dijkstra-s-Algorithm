
//Author - Asel Siriwardena
//IIT ID: 201754
//UOW ID: 1698419

import java.lang.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class FlowNetwork {
    //static int numberOfNodes = 0;
    //Number of nodes in the graph
    static int numberOfNodes = 0;


    boolean breathFSearch(int rGraph[][], int s, int t, int parent[]) {
        //Doing a breath-first search to find the shortest path
        // Create a isVisited array and mark all vertices as not
        // isVisited
        boolean isVisited[] = new boolean[numberOfNodes];
        for (int i = 0; i < numberOfNodes; ++i)
            isVisited[i] = false;


        // source,vertex as isVisited
        LinkedList<Integer> queueLinkedList = new LinkedList<Integer>();
        queueLinkedList.add(s);
        isVisited[s] = true;
        parent[s] = -1;

        // Standard Breath-First Search Loop
        while (queueLinkedList.size() != 0) {
            int u = queueLinkedList.poll();

            for (int v = 0; v < numberOfNodes; v++) {
                if (isVisited[v] == false && rGraph[u][v] > 0) {
                    queueLinkedList.add(v);
                    parent[v] = u;
                    isVisited[v] = true;
                }
            }
        }

        //doing a breath first search from the source,it is reached the sink it will return true.otherwise false.
        return (isVisited[t] == true);
    }

    // Returns tne maximum flow
    int ffAlgorithm(int graph[][], int s, int t) {
        int u, v;

        //Creates a residual graph by coping the user input values of generated values in the original graph
        int rGraph[][] = new int[numberOfNodes][numberOfNodes];

        for (u = 0; u < numberOfNodes; u++)
            for (v = 0; v < numberOfNodes; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[numberOfNodes];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while there is path from source
        // to sink
        while (breathFSearch(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
                EdgeNodeClass.getEdgeList().add(new EdgeNodeClass(u, v, path_flow));
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;

            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    // Driver program to test above functions
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Flow Network Generator ");
        System.out.print("If you want to generate nodes,capacities and edges manually press y \n" +
                "else just press enter ! (y/Y) ?  - ");
        String userChoice = sc.nextLine();


        if (userChoice.equalsIgnoreCase("y")) {

            System.out.print("Enter number of nodes : ");

            while (true) {
                try {
                    numberOfNodes = sc.nextInt();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Input is not valid !");
                }
            }

            Utilities.nodeCreator(numberOfNodes);

            System.out.println("Now give capacities to these edges : ");

            int graph[][] = new int[numberOfNodes][numberOfNodes];

            for (int startNode = 0; startNode < numberOfNodes - 1; startNode++) {
                for (int innerNode = 1; innerNode < numberOfNodes; innerNode++) {
                    if (startNode == innerNode) {
                        graph[startNode][innerNode] = 0;
                    } else {
                        String nodeBegin = null;
                        String nodeDesti = null;

                        if (startNode == 0) {
                            nodeBegin = "S";
                        } else {
                            nodeBegin = Integer.toString(startNode);
                        }

                        if (innerNode == numberOfNodes - 1) {
                            nodeDesti = "T";
                        } else {
                            nodeDesti = Integer.toString(innerNode);
                        }

                        if (graph[innerNode][startNode] == 0) {
                            System.out.print("Enter edge capacity " + nodeBegin + " => " + nodeDesti + " : ");
                            int input = sc.nextInt();
                            graph[startNode][innerNode] = input;
                            Grapher.graphMaker(false, nodeBegin, nodeDesti, Integer.toString(input));
                        } else {
                            System.out.print("Enter edge capacity " + nodeBegin + " => " + nodeDesti + " : ");
                            int input = sc.nextInt();
                            graph[startNode][innerNode] = input;
                            Grapher.graphMaker(true, nodeBegin, Integer.toString(innerNode), Integer.toString(input));
                        }
                    }
                }
            }


            FlowNetwork m = new FlowNetwork();
            System.out.println("\n" + "Number of nodes : " + numberOfNodes);

            long startTime = System.nanoTime();

            int maxFlow = m.ffAlgorithm(graph, 0, numberOfNodes - 1);

            long endTime = System.nanoTime();

            long totalTime = endTime - startTime;


            Utilities.displayAugmentingPaths();
            System.out.println("\nThe maximum possible flow is " + maxFlow);
            System.out.println("Algorithm running time : " + (double) totalTime / 1000000.0);

            Grapher.displayGraph();

        } else {


            numberOfNodes = ThreadLocalRandom.current().nextInt(6, 13);
            int graph[][] = new int[numberOfNodes][numberOfNodes];
            Utilities.nodeCreator(numberOfNodes);

            for (int startNode = 0; startNode < numberOfNodes; startNode++) {
                System.out.print("\n" + 0 + " ");
                for (int innerNode = 1; innerNode < numberOfNodes; innerNode++) {
                    if (startNode == innerNode) {
                        graph[startNode][innerNode] = 0;
                        System.out.printf("%5d", 0);
                    } else {
                        if (startNode == numberOfNodes - 1) {
                            System.out.printf("%5d", 0);
                        } else {
                            if (ThreadLocalRandom.current().nextBoolean()) {
                                int ran = ThreadLocalRandom.current().nextInt(5, 21);
                                graph[startNode][innerNode] = ran;
                                System.out.printf("%5d", ran);

                                String nodeBegin = null;
                                String nodeDesti = null;

                                if (startNode == 0) {
                                    nodeBegin = "S";
                                } else {
                                    nodeBegin = Integer.toString(startNode);
                                }

                                if (innerNode == numberOfNodes - 1) {
                                    nodeDesti = "T";
                                } else {
                                    nodeDesti = Integer.toString(innerNode);
                                }

                                if (graph[innerNode][startNode] == 0) {
                                    Grapher.graphMaker(false, nodeBegin, nodeDesti, Integer.toString(ran));
                                } else {
                                    Grapher.graphMaker(true, nodeBegin, Integer.toString(innerNode), Integer.toString(ran));
                                }

                            } else {
                                graph[startNode][innerNode] = 0;
                                System.out.printf("%5d", 0);
                            }
                        }
                    }
                }
            }

            FlowNetwork m = new FlowNetwork();
            System.out.println("\n" + "Number of nodes : " + numberOfNodes);

            long startTime = System.nanoTime();

            int maxFlow = m.ffAlgorithm(graph, 0, numberOfNodes - 1);

            long endTime = System.nanoTime();

            long totalTime = endTime - startTime;


            Utilities.displayAugmentingPaths();
            System.out.println("\nThe maximum possible flow is " + maxFlow);
            //System.out.println("Number of Edges : " + EdgeNodeClass.getEdgeList().size());
            System.out.println("Algorithm running time : " + ((double) totalTime / 1000000.0));

            Grapher.displayGraph();

        }

    }
}
