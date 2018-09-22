import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.scoring.*;


public class RealWorldHeterogeneousNetworkAutoInput {

	public static void main(String[] args) throws IOException {            
        SimpleWeightedGraph<String, DefaultEdge> simpleWeightedStringGraph = createSimpleWeightedStringGraph();
        //System.out.println("simpleWeightedStringGraph.toString()" + simpleWeightedStringGraph.toString());
        
        PageRank<String, DefaultEdge> prTesterOne = new PageRank<String, DefaultEdge>(simpleWeightedStringGraph);
        
        System.out.println("prTesterOne.getScores()" + prTesterOne.getScores());
        //System.out.println("LawrenceM.Brown: " + prTesterOne.getVertexScore("LawrenceM.Brown"));
        //System.out.println("Paper 1.: " + prTesterOne.getVertexScore("Paper 1."));
	}
	
    
    /**
     * Create a toy simple weighted graph based on String objects.
     *
     * @return a simple weighted graph based on String objects.
     */
    private static SimpleWeightedGraph<String, DefaultEdge> createSimpleWeightedStringGraph() throws IOException
    {
    	SimpleWeightedGraph<String, DefaultEdge> gOne = new SimpleWeightedGraph<>(DefaultEdge.class);

    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDataset\\RWSlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDataset\\RWToy-meta.txt";
    	
    	//DM
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\V1DMTesting16May\\DMSlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\V1DMTesting16May\\DM-meta.txt";
    	
    	//Real World Toy Dataset
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDatasetTesting\\RWTOYSlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDatasetTesting\\RWToy-meta.txt";
    	
    	//AI
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\AITesting\\AISlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\AITesting\\AI-meta.txt";
    	
    	//AAN
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\AANTesting\\AANSlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\AANTesting\\acl-metadata.txt";
    	
    	//CGM
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\CGMTesting\\CGMSlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\CGMTesting\\CGM-meta.txt";
    	
    	//IS
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\ISTesting\\ISSlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\ISTesting\\IS-meta.txt";
    	
    	//SE
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\SETesting\\SESlpa.txt";
    	//String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\SETesting\\SE-meta.txt";
    	
    	//Digg
    	String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\DiggTwitterResults\\DiggUserReplyCommentStoryKTLE8-slpa.txt";
    	String inputFilePathOne = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\DiggTwitterResults\\DiggUserReplyCommentStoryKTLE8-meta.txt";

    	
		FileReader fr = new FileReader(inputFilePath);
		BufferedReader br = new BufferedReader(fr);
		
		FileReader frOne = new FileReader(inputFilePathOne);
		BufferedReader brOne = new BufferedReader(frOne);
		
		ArrayList<String> addedVertex = new ArrayList<String>();
		
		try {
				String s; 
				while((s = br.readLine()) != null) {
					String[] lineItems;
					lineItems = s.split("\\t");
					String firstAuthorName;
					String secondAuthorName;
					
					firstAuthorName = lineItems[0].trim().replaceAll("\\s","").replace(";","").replace(",","");;
					if (!addedVertex.contains(firstAuthorName)){
						addedVertex.add(firstAuthorName);
						gOne.addVertex(firstAuthorName);
						//System.out.println("firstAuthorName: " + firstAuthorName);
					}
					
					secondAuthorName = lineItems[1].trim().replaceAll("\\s","").replace(";","").replace(",","");;
					if (!addedVertex.contains(secondAuthorName)){
						addedVertex.add(secondAuthorName);
						gOne.addVertex(secondAuthorName);
						//System.out.println("secondAuthorName: " + secondAuthorName);
					}
					
					gOne.addEdge(firstAuthorName, secondAuthorName);
					
					//System.out.println("after adding edge firstAuthorName: " + firstAuthorName);
					//System.out.println("after adding edge secondAuthorName: " + secondAuthorName);
					
			        DefaultEdge currentEdge = gOne.getEdge(firstAuthorName, secondAuthorName);
			        double edgeWeight = Double.parseDouble(lineItems[2]);
			        gOne.setEdgeWeight(currentEdge, edgeWeight);
					
				}
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			//System.out.println("Exception");
		}
		
		finally {
			fr.close();
			br.close();
		}
		
		
		
		try {
			String sOne;
			ArrayList<String> processedAuthorNames = new ArrayList<String>();
			while((sOne = brOne.readLine()) != null) {
				if (sOne.contains("author = {")){
					String extractedAuthorNames;
					String[] splitAuthorNames;
					processedAuthorNames.clear();
					extractedAuthorNames = sOne.substring(10, sOne.length()-1);
					//System.out.println("extractedAuthorNames: " + extractedAuthorNames);
					splitAuthorNames = extractedAuthorNames.split(";");
					for (String splitAuthorName : splitAuthorNames){
						String processedAuthorName = splitAuthorName.trim().replaceAll("\\s","").replace(";","").replace(",","");
						//System.out.println("processedAuthorName: " + processedAuthorName);
						processedAuthorNames.add(processedAuthorName);
						if (!addedVertex.contains(processedAuthorName)){
							addedVertex.add(processedAuthorName);
							gOne.addVertex(processedAuthorName);
						}
					}	
				}
				
				if (sOne.contains("title = {")){
					String extractedPaperName;
					extractedPaperName = "paperrrrrr " + sOne.substring(9, sOne.length()-1).trim().replace(",","");
					//System.out.println("extractedPaperName: " + extractedPaperName);
					if (!addedVertex.contains(extractedPaperName)){
						addedVertex.add(extractedPaperName);
						gOne.addVertex(extractedPaperName);
					}
					
					for (int i = 0; i < processedAuthorNames.size(); i++) {
						gOne.addEdge(processedAuthorNames.get(i), extractedPaperName);
					}
				}
				
			}
	}
	
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	catch (IOException e) {
		//System.out.println("Exception");
	}
	
	finally {
		frOne.close();
		brOne.close();
	}
    	
        return gOne;
    }
}

