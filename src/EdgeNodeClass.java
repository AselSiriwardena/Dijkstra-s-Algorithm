
//Author - Asel Siriwardena
//IIT ID: 201754
//UOW ID: 1698419

import java.util.ArrayList;

public class EdgeNodeClass {

    private static ArrayList<EdgeNodeClass> edgeList = new ArrayList<>();


    private int staringNode;
    private int destNode;
    private int capacity;

    public EdgeNodeClass(int staringNode, int destNode, int capacity) {
        this.staringNode = staringNode;
        this.destNode = destNode;
        this.capacity = capacity;
    }

    public static ArrayList<EdgeNodeClass> getEdgeList() {
        return edgeList;
    }


    public int getStaringNode() {
        return staringNode;
    }

    public int getDestNode() {
        return destNode;
    }

    public int getCapacity() {
        return capacity;
    }
}
