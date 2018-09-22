import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.scoring.*;


public class RealWorldHeterogeneousNetwork {

	public static void main(String[] args) {            
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        System.out.println("simpleWeightedStringGraph.toString()" + simpleWeightedStringGraph.toString());
        
        PageRank<String, DefaultEdge> prTesterOne = new PageRank<String, DefaultEdge>(simpleWeightedStringGraph);
        System.out.println("prTesterOne.getScores()" + prTesterOne.getScores());
        System.out.println("Lawrence: " + prTesterOne.getVertexScore("Lawrence"));
        System.out.println("Paper 1: " + prTesterOne.getVertexScore("Paper 1"));
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
        String v6 = "Paper 1";
        String v7 = "Paper 2";
        String v8 = "Paper 3";
        String v9 = "Paper 4";
        String v10 = "Paper 5";
        String v11 = "Paper 6";
        String v12 = "Paper 7";
        String v13 = "Paper 8";

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
        gOne.addVertex(v11);
        gOne.addVertex(v12);
        gOne.addVertex(v13);

        // add the edges
        gOne.addEdge(v1, v4);
        gOne.addEdge(v1, v3);
        gOne.addEdge(v4, v3);
        gOne.addEdge(v3, v2);
        gOne.addEdge(v4, v5);
        gOne.addEdge(v3, v5);
        
        gOne.addEdge(v6, v3);
        gOne.addEdge(v6, v4);
        gOne.addEdge(v6, v5);
        
        gOne.addEdge(v7, v3);
        gOne.addEdge(v7, v4);
        gOne.addEdge(v7, v5);
        
        gOne.addEdge(v8, v3);
        gOne.addEdge(v8, v4);
        gOne.addEdge(v8, v5);
        
        gOne.addEdge(v9, v3);
        gOne.addEdge(v9, v5);
        
        gOne.addEdge(v10, v3);
        gOne.addEdge(v10, v5);
        
        gOne.addEdge(v11, v1);
        gOne.addEdge(v11, v4);
        
        gOne.addEdge(v12, v1);
        gOne.addEdge(v12, v3);
        
        gOne.addEdge(v13, v2);
        gOne.addEdge(v13, v3);
        
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
