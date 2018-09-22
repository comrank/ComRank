import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.scoring.*;


public class RealWorldHomogeneousNetwork {

	public static void main(String[] args) {            
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        System.out.println(simpleWeightedStringGraph.toString());
        
        PageRank<String, DefaultEdge> prTesterOne = new PageRank<String, DefaultEdge>(simpleWeightedStringGraph);
        System.out.println(prTesterOne.getScores());
        System.out.println(prTesterOne.getVertexScore("Lawrence"));
	}
	
    
    /**
     * Create a toy simple weighted graph based on String objects.
     *
     * @return a simple weighted graph based on String objects.
     */
    private static SimpleWeightedGraph<String, DefaultEdge> createSimpleWeightedStringGraph()
    {
    	SimpleWeightedGraph<String, DefaultEdge> gOne = new SimpleWeightedGraph<>(DefaultEdge.class);

        String v1 = "Murat";
        String v2 = "Biljana";
        String v3 = "Lawrence";
        String v4 = "Senol";
        String v5 = "Riza";

        // add the vertices
        gOne.addVertex(v1);
        gOne.addVertex(v2);
        gOne.addVertex(v3);
        gOne.addVertex(v4);
        gOne.addVertex(v5);

        // add the edges
        gOne.addEdge(v1, v4);
        gOne.addEdge(v1, v3);
        gOne.addEdge(v4, v3);
        gOne.addEdge(v3, v2);
        gOne.addEdge(v4, v5);
        gOne.addEdge(v3, v5);
        
        // set the edge weights
        //gOne.setEdgeWeight(v1, v2, 8d);
        DefaultEdge currentEdge = gOne.getEdge(v4, v3);
        gOne.setEdgeWeight(currentEdge, 3d);
        
        DefaultEdge currentEdgeOne = gOne.getEdge(v4, v5);
        gOne.setEdgeWeight(currentEdgeOne, 3d);
        
        DefaultEdge currentEdgeTwo = gOne.getEdge(v3, v5);
        gOne.setEdgeWeight(currentEdgeTwo, 5d);

        return gOne;
    }

}
