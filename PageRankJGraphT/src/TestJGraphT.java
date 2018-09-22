import java.net.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import org.jgrapht.alg.scoring.*;


public class TestJGraphT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<String, DefaultEdge> stringGraph = createStringGraph();

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println(stringGraph.toString());

        // create a graph based on URL objects
        Graph<URL, DefaultEdge> hrefGraph = createHrefGraph();

        // note directed edges are printed as: (<v1>,<v2>)
        System.out.println(hrefGraph.toString());
        
        //monica 05/01/2018 PageRank Testing undirected edges
        PageRank<String, DefaultEdge> prTester = new PageRank<String, DefaultEdge>(stringGraph);
        System.out.println(prTester.getScores());
        System.out.println(prTester.getVertexScore("v1"));
        
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        System.out.println(simpleWeightedStringGraph.toString());
        PageRank<String, DefaultEdge> prTesterOne = new PageRank<String, DefaultEdge>(simpleWeightedStringGraph);
        System.out.println(prTesterOne.getScores());
        
        //monica 06/01/2018 PageRank Testing directed edges
        PageRank<URL, DefaultEdge> prTesterTwo = new PageRank<URL, DefaultEdge>(hrefGraph);
        System.out.println(prTesterTwo.getScores());
        
    }

    /**
     * Creates a toy directed graph based on URL objects that represents link structure.
     *
     * @return a graph based on URL objects.
     */
    private static Graph<URL, DefaultEdge> createHrefGraph()
    {
        Graph<URL, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        try {
            URL amazon = new URL("http://www.amazon.com");
            URL yahoo = new URL("http://www.yahoo.com");
            URL ebay = new URL("http://www.ebay.com");

            // add the vertices
            g.addVertex(amazon);
            g.addVertex(yahoo);
            g.addVertex(ebay);

            // add edges to create linking structure
            g.addEdge(yahoo, amazon);
            g.addEdge(yahoo, ebay);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return g;
    }

    /**
     * Create a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    private static Graph<String, DefaultEdge> createStringGraph()
    {
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }
	
    
    /**
     * Create a toy simple weighted graph based on String objects.
     *
     * @return a simple weighted graph based on String objects.
     */
    private static SimpleWeightedGraph<String, DefaultEdge> createSimpleWeightedStringGraph()
    {
    	SimpleWeightedGraph<String, DefaultEdge> gOne = new SimpleWeightedGraph<>(DefaultEdge.class);

        String v1 = "p";
        String v2 = "v";
        String v3 = "w";
        String v4 = "z";
        String v5 = "q";

        // add the vertices
        gOne.addVertex(v1);
        gOne.addVertex(v2);
        gOne.addVertex(v3);
        gOne.addVertex(v4);
        gOne.addVertex(v5);

        // add edges to create a circuit
        gOne.addEdge(v1, v2);
        gOne.addEdge(v2, v3);
        gOne.addEdge(v3, v4);
        gOne.addEdge(v4, v1);
        
        //gOne.setEdgeWeight(v1, v2, 8d);
        DefaultEdge currentEdge = gOne.getEdge(v1, v2);
        gOne.setEdgeWeight(currentEdge, 8d);

        return gOne;
    }

    
}
