import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.scoring.*;


public class SyntheticHeterogeneousNetwork {

	public static void main(String[] args) {            
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        System.out.println(simpleWeightedStringGraph.toString());
        
        PageRank<String, DefaultEdge> prTesterOne = new PageRank<String, DefaultEdge>(simpleWeightedStringGraph);
        System.out.println(prTesterOne.getScores());
        System.out.println(prTesterOne.getVertexScore("Emma"));
        System.out.println(prTesterOne.getVertexScore("DB Paper 1"));
	}
	
    
    /**
     * Create a toy simple weighted graph based on String objects.
     *
     * @return a simple weighted graph based on String objects.
     */
    private static SimpleWeightedGraph<String, DefaultEdge> createSimpleWeightedStringGraph()
    {
    	SimpleWeightedGraph<String, DefaultEdge> gOne = new SimpleWeightedGraph<>(DefaultEdge.class);

        String v1 = "Bob";
        String v2 = "John";
        String v3 = "Dan";
        String v4 = "Emma";
        String v5 = "James";
        String v6 = "Security Paper";
        String v7 = "DM Paper";
        String v8 = "IR Paper";
        String v9 = "AI Paper";
        String v10 = "DB Paper 1";
        String v11 = "DB Paper 2";
        String v12 = "DB Paper 3";      

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

        // add the edges
        gOne.addEdge(v1, v2);
        gOne.addEdge(v2, v3);
        gOne.addEdge(v2, v4);
        gOne.addEdge(v3, v4);
        gOne.addEdge(v4, v5);
        
        gOne.addEdge(v6, v1);
        gOne.addEdge(v6, v2);
        
        gOne.addEdge(v7, v2);
        gOne.addEdge(v7, v3);
        
        gOne.addEdge(v8, v3);
        gOne.addEdge(v8, v4);
        
        gOne.addEdge(v9, v2);
        gOne.addEdge(v9, v4);
        
        gOne.addEdge(v10, v4);
        gOne.addEdge(v10, v5);
        
        gOne.addEdge(v11, v4);
        gOne.addEdge(v11, v5);
        
        gOne.addEdge(v12, v4);
        gOne.addEdge(v12, v5);
        
        //set the edge weights
        //gOne.setEdgeWeight(v1, v2, 8d);
        DefaultEdge currentEdge = gOne.getEdge(v4, v5);
        gOne.setEdgeWeight(currentEdge, 3d);

        return gOne;
    }

}
