import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.scoring.*;


public class SyntheticHomogeneousNetworkV1 {

	public static void main(String[] args) {            
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        System.out.println(simpleWeightedStringGraph.toString());
        
        PageRank<String, DefaultEdge> prTesterOne = new PageRank<String, DefaultEdge>(simpleWeightedStringGraph);
        System.out.println(prTesterOne.getScores());
        //System.out.println(prTesterOne.getVertexScore("A"));
        //System.out.println(prTesterOne.getVertexScore("B"));
        //System.out.println(prTesterOne.getVertexScore("C"));
	}
	
    
    /**
     * Create a toy simple weighted graph based on String objects.
     *
     * @return a simple weighted graph based on String objects.
     */
    private static SimpleWeightedGraph<String, DefaultEdge> createSimpleWeightedStringGraph()
    {
    	SimpleWeightedGraph<String, DefaultEdge> gOne = new SimpleWeightedGraph<>(DefaultEdge.class);

        String v1 = "A";
        String v2 = "B";
        String v3 = "C";
        //String v4 = "Emma";
        //String v5 = "James";

        // add the vertices
        gOne.addVertex(v1);
        gOne.addVertex(v2);
        gOne.addVertex(v3);
        //gOne.addVertex(v4);
        //gOne.addVertex(v5);

        // add the edges
        gOne.addEdge(v1, v2);
        gOne.addEdge(v2, v3);
        gOne.addEdge(v1, v3);
        //gOne.addEdge(v3, v4);
        //gOne.addEdge(v4, v5);
        
        // set the edge weights
        //gOne.setEdgeWeight(v1, v2, 8d);
        //DefaultEdge currentEdge = gOne.getEdge(v4, v5);
        //gOne.setEdgeWeight(currentEdge, 3d);

        return gOne;
    }

}
