
//Author - Asel Siriwardena
//IIT ID: 201754
//UOW ID: 1698419

import java.util.Collections;

public class Utilities {
    public static void getInt(){

    }

    public static void nodeCreator(int numberOfNodes){

        for (int i = 0; i < numberOfNodes; i++) {
            String nodeName = null;
            if (i == 0) {
                nodeName = "S";
            } else if (i == numberOfNodes - 1) {
                nodeName = "T";
            } else {
                nodeName = Integer.toString(i);
            }
            Grapher.getGraph().addNode(nodeName).addAttribute("xy", 0, 1);
        }

    }

    public static void displayAugmentingPaths(){
        System.out.println("These are the identified augmenting paths");
        Collections.reverse(EdgeNodeClass.getEdgeList());
        for (EdgeNodeClass edge : EdgeNodeClass.getEdgeList()){
            System.out.println("Start : " + edge.getStaringNode() + " Destination : " + edge.getDestNode() + " Capacity : " + edge.getCapacity());
        }
    }
}
