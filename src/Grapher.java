
//Author - Asel Siriwardena
//IIT ID: 201754
//UOW ID: 1698419


import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Grapher{

    private static MultiGraph graph = new MultiGraph("Network");

    public static MultiGraph getGraph() {
        return graph;
    }

    public static void graphMaker(Boolean isMulti,String start,String end,String capacity){

        String offSet = "text-offset: 20;";

        if (isMulti){
            offSet = "text-offset: -20;";
        }
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");


        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "edge {text-alignment: along;}");


        Edge ab = graph.addEdge(start.concat(end), start, end, true);

        ab.setAttribute("ui.style",offSet);

        ab.setAttribute("label", capacity);

    }

    public static void displayGraph(){
        for (Node n : Grapher.getGraph()) {

            n.addAttribute("label", n.getId());
            n.addAttribute("ui.style", "shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
        }

        Grapher.getGraph().display();

    }


}