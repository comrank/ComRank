import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.scoring.*;


public class SyntheticHomogeneousNetworkV6 {

	public static void main(String[] args) {            
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        //System.out.println(simpleWeightedStringGraph.toString());
        
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
        String v4 = "D";
        String v5 = "E";

        String v6 = "A1";
        String v7 = "B1";
        String v8 = "C1";
        String v9 = "D1";
        String v10 = "E1";

        // add the vertices
        gOne.addVertex(v1);
        gOne.addVertex(v2);
        gOne.addVertex(v3);
        gOne.addVertex(v4);
        gOne.addVertex(v5);
        
        gOne.addVertex(v6);
        gOne.addVertex(v7);
        gOne.addVertex(v8);
        gOne.addVertex(v9);
        gOne.addVertex(v10);

        // add the edges
        gOne.addEdge(v1, v2);
        gOne.addEdge(v2, v3);
        gOne.addEdge(v1, v3);
        gOne.addEdge(v1, v4);
        gOne.addEdge(v1, v5);
        gOne.addEdge(v2, v4);
        gOne.addEdge(v3, v5);
        
        gOne.addEdge(v6, v7);
        gOne.addEdge(v6, v8);
        gOne.addEdge(v6, v9);
        gOne.addEdge(v6, v10);
        gOne.addEdge(v7, v9);
        gOne.addEdge(v8, v10);
        
        // set the edge weights
        //gOne.setEdgeWeight(v1, v2, 8d);
        //DefaultEdge currentEdge = gOne.getEdge(v4, v5);
        //gOne.setEdgeWeight(currentEdge, 3d);

        return gOne;
    }

}
