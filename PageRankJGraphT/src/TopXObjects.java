import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;


public class TopXObjects {
	
	public static boolean DESC = false;
	public static int topX = 1000;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//DM
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\V1DMTesting16May\\PageRankOutput.txt";
    	
		//Real World Toy Dataset
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDatasetTesting\\PageRankOutput.txt";
		
		//AI
		//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\AITesting\\PageRankOutput.txt";
		
		//CGM
		//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\CGMTesting\\PageRankOutput.txt";
		
		//IS
		//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\ISTesting\\PageRankOutput.txt";
		
		//SE
		//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\SETesting\\PageRankOutput.txt";
		
		//AAN
		//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\AANTesting\\PageRankOutput.txt";
		
		//SampleNetworkV3
		//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\SampleNetworkV3\\PageRankOutput.txt";
		
		//SampleNetworkV6
		String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\SampleNetworkV6\\PageRankOutput.txt";

		FileReader fr = new FileReader(inputFilePath);
		BufferedReader br = new BufferedReader(fr);		
		
		String valueContent;
		
		Map<String, Double> unsortMap = new HashMap<String, Double>();
		Map<String, Double> OHCRObjectValue = new HashMap<String, Double>();
				
		try {
			String s; 
			while((s = br.readLine()) != null) {
				valueContent = s.substring(s.indexOf('{')+1, s.indexOf('}'));
				//System.out.println(valueContent);
				String[] prItems;
				prItems = valueContent.split(",");
				for (String eachPrItem : prItems){
					//System.out.println(eachPrItem);
					String[] eachPrItemContents = eachPrItem.split("=");
					String objectName = eachPrItemContents[0].trim();
					System.out.println(objectName);
					String objectPageRankValue = eachPrItemContents[1].trim();
					//System.out.println(objectPageRankValue);
					double objectPageRankValueDouble = Double.parseDouble(objectPageRankValue);
					unsortMap.put(objectName, objectPageRankValueDouble);
				}
				
			}
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			System.out.println("Exception");
		}
		
		finally {
			fr.close();
			br.close();
		}


	    //System.out.println("Before sorting......");
	    //printMap(unsortMap);
	    
        //System.out.println("After sorting descindeng order......");
        //Map<String, Double> sortedMapDesc = sortByComparator(unsortMap, DESC);
        //printMap(sortedMapDesc);
        
        System.out.println("Generating objects with top " + topX + " page rank values......");
        Map<String, Double> sortedMapDescTopOjbects = sortByComparator(unsortMap, DESC);
        printTopXInMap(sortedMapDescTopOjbects);
        
/*        System.out.println("Generating X objects with top OHCR values......");
        Map<String, Double> sortedTopOHCRObjects = sortByComparator(OHCRObjectValue, DESC);
        printTopXInMap(sortedTopOHCRObjects);*/
        
        
        int comNum = 1;
        String commPaperName = "";
        String paperrrrrrCommPaperName = "";
        String processedPaperName = "";
        String commAuthorNamesStr;
        String[] commPaperNames = {};
        String[] commAuthorNames = {};
        String commAuthorName = "";
        int commWeighting = 1;
        double paperPageRankValue;
        double authorPageRankValue;
        double communityPageRankValue = 0.0;
        List<Double> allCommPageRankValues = new ArrayList<Double>();
        String currentObject;
        Double objectValue = 0.0;
        Double originalObjectPRValue = 0.0;
        ArrayList<String> problematicPapers = new ArrayList<String>();
        
        //ArrayList<String> communityMembers = new ArrayList<String>();
        Map<ArrayList<String>, Double> communityMap = new LinkedHashMap<ArrayList<String>, Double>();
        //Map<String, Double> OHCRObjectValue = new HashMap<String, Double>();
        
        //DM
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\DM\\Communities.txt";
        
        //AI
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\AI\\Communities.txt";
        
    	//CGM
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\CGM\\Communities.txt";
    	
    	//IS
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\IS\\Communities.txt";
    	
    	//SE
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\SE\\Communities.txt";
    	
        //AAN
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\AAN\\Communities.txt";
        
        //Real world toy dataset
        //String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\NeededOHCWork\\domains\\RWTOY\\pureCommunities.txt";
    	
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDatasetTesting\\PageRankOutput.txt";
        
        //SampleNetworkV3
    	//String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\SampleNetworkV3\\Communities.txt";

    	//SampleNetworkV6
    	String inputOHCCommunityFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\OHCFinalDetectedCommunitiesNoTriangleLessThanTwoPapersNoCommunity\\SampleNetworkV6\\Communities.txt";

		FileReader frComm = new FileReader(inputOHCCommunityFilePath);
		BufferedReader brComm = new BufferedReader(frComm);		
		
		System.out.println("OHCR algorithm begins......");
		//OHCR Algorithm
		try {
			String sComm; 
			while((sComm = brComm.readLine()) != null) {
				if (sComm.contains("Paper: [")){
					commPaperName = sComm.substring(sComm.indexOf("[")+1, sComm.length()-1).trim().replace(",","");
					//System.out.println(commPaperName);
				}
				
				if (sComm.contains("Community: ")){
					commAuthorNamesStr = sComm.substring(sComm.indexOf(":")+1, sComm.length()-1);
					commAuthorNames = commAuthorNamesStr.split(" - ");
					for (String authorName : commAuthorNames){
						commAuthorName = authorName.trim().replaceAll("\\s","").replace(";","").replace(",","");
						//System.out.println(commAuthorName);
					}
					
				}
				
				if (sComm.contains("Weighting: ")){
					System.out.println(sComm.substring(11, sComm.length()));
					commWeighting = Integer.parseInt(sComm.substring(11, sComm.length()));
					//System.out.println("Community weighting is: " + commWeighting);
				}
				
				if (sComm.equals("")){
					ArrayList<String> communityMembers = new ArrayList<String>();
					//communityMembers.clear();
					System.out.println("Community Number: " + comNum);
					if (commWeighting == 1){
						paperrrrrrCommPaperName = "paperrrrrr " + commPaperName;
						System.out.println("weighting is 1: " + paperrrrrrCommPaperName);
						communityMembers.add(paperrrrrrCommPaperName);
						if (unsortMap.get(paperrrrrrCommPaperName) != null){
							paperPageRankValue = unsortMap.get(paperrrrrrCommPaperName);
							//System.out.println(paperPageRankValue);
							communityPageRankValue += paperPageRankValue;
						}else{
							System.out.println("unable to find the paper name (weighting 1 scenario): " + paperrrrrrCommPaperName);
						}

					}else{
						//System.out.println("weighting is NOT 1: " + commPaperName);
						commPaperNames = commPaperName.split("\\.");
						for (String paperName : commPaperNames){
							//System.out.println("I am here: " + paperName);
							processedPaperName = "paperrrrrr " + paperName.trim().concat(".");
							communityMembers.add(processedPaperName);
							System.out.println("weighting is NOT 1 processedPaperName: " + processedPaperName);
							if (unsortMap.get(processedPaperName) != null){
								paperPageRankValue = unsortMap.get(processedPaperName);
								//System.out.println(paperPageRankValue);
								communityPageRankValue += paperPageRankValue;
							}else{
								System.out.println("unable to find the paper name: " + processedPaperName);
								if (!problematicPapers.contains(processedPaperName)){
									problematicPapers.add(processedPaperName);
								}
							}

						}
					}

					for (String authorName : commAuthorNames){
						commAuthorName = authorName.trim().replaceAll("\\s","").replace(";","").replace(",","");
						System.out.println(commAuthorName);
						communityMembers.add(commAuthorName);
						if (unsortMap.get(commAuthorName) != null) {
							authorPageRankValue = unsortMap.get(commAuthorName);
							//System.out.println(authorPageRankValue);
							communityPageRankValue += authorPageRankValue;
						}else{
							System.out.println("unable to find the author name: " + commAuthorName);
						}
					}
					
					System.out.println("page rank value of the community is: " + communityPageRankValue);
					//14 May
					//System.out.println("members of the community are: " + communityMembers);
					communityMap.put(communityMembers, communityPageRankValue);
					//14 May
					//System.out.println("KEY SET: " +communityMap.keySet());
					allCommPageRankValues.add(communityPageRankValue);
					communityPageRankValue = 0.0;
					comNum++;
				}
				
			}
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			System.out.println("Exception");
		}
		
		finally {
			frComm.close();
			brComm.close();
		}
		
		
		//System.out.println("Maximum community page rank values is: "+ Collections.max(allCommPageRankValues));
		Double maxCommPRValue = Collections.max(allCommPageRankValues);
		System.out.println("Maximum community page rank values is: "+ maxCommPRValue);
		
		
		System.out.println("Size of problematic papers is: "+ problematicPapers.size());
		System.out.println("Problematic papers are as follows: " + problematicPapers);
		
		//core part of OHCR
		System.out.println("Generating final scores of objects......");
		
		
/*        for (Entry<String, Double> entry : sortedMapDescTopOjbects.entrySet()){
        	//System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        	currentObject = entry.getKey();
        	System.out.println("Ojbect : " + entry.getKey());
        	objectValue = entry.getValue();
        	originalObjectPRValue = entry.getValue();
        	
        	for (Entry<ArrayList<String>, Double> commMemberEntry : communityMap.entrySet()){
        		System.out.println("YES I have got here!");
        		if (commMemberEntry.getKey().contains(currentObject)){
        			System.out.println("YES YES I have got here TWO!!");
        			objectValue += originalObjectPRValue * (commMemberEntry.getValue()/maxCommPRValue);
        		}
        	}
        	
        	System.out.println("Score : " + objectValue);
        	
        	objectValue = 0.0;
        	originalObjectPRValue = 0.0;
        }*/
        
        
	     //iterate over a map
	     Iterator<String> itOHCR= sortedMapDescTopOjbects.keySet().iterator();
	     while (itOHCR.hasNext()) {
	          String keyInMapOHCR = itOHCR.next();
	          //System.out.println("Top " + currentX + " object:   " + "Key : " + keyInMap + " Value : "+ map.get(keyInMap));
	          currentObject = keyInMapOHCR;
	          System.out.println("Ojbect : " + currentObject);
	          objectValue = sortedMapDescTopOjbects.get(keyInMapOHCR);
	          originalObjectPRValue = sortedMapDescTopOjbects.get(keyInMapOHCR);
	          
/*	        	for (Entry<ArrayList<String>, Double> commMemberEntry : communityMap.entrySet()){
	        		System.out.println("YES I have got here!");
	        		if (commMemberEntry.getKey().contains(currentObject)){
	        			System.out.println("YES YES I have got here TWO!!");
	        			objectValue += originalObjectPRValue * (commMemberEntry.getValue()/maxCommPRValue);
	        		}
	        	}*/
	          
	          //System.out.println(communityMap.size());
	          //System.out.println(communityMap.keySet());
	          
	          
	          Iterator<ArrayList<String>> itOHCROne= communityMap.keySet().iterator();
	          while (itOHCROne.hasNext()) {
	        	  ArrayList<String> keyInMapOHCROne = itOHCROne.next();
	        	  //14 May
	        	  //System.out.println(keyInMapOHCROne);
	        	  if (keyInMapOHCROne.contains(currentObject)){
	        		  //14 May
	        		  //System.out.println("YES YES YES I have got here THREE!!!");
	        		  objectValue += originalObjectPRValue * (communityMap.get(keyInMapOHCROne)/maxCommPRValue);
	        	  }
	          }
	          
	        	
	          System.out.println("Score : " + objectValue);
	          OHCRObjectValue.put(currentObject, objectValue);
	        	
	          objectValue = 0.0;
	          originalObjectPRValue = 0.0;
	      }
	     
	     
	     System.out.println("Generating objects with top " + topX + " OHCR values......");
	     Map<String, Double> sortedTopOHCRObjects = sortByComparator(OHCRObjectValue, DESC);
	     printTopXInMap(sortedTopOHCRObjects);
	     
	    
		//OHCR Algorithm

	}
		
	 private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order)
	    {

	        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<String, Double>>()
	        {
	            public int compare(Entry<String, Double> o1,
	                    Entry<String, Double> o2)
	            {
	                if (order)
	                {
	                    return o1.getValue().compareTo(o2.getValue());
	                }
	                else
	                {
	                    return o2.getValue().compareTo(o1.getValue());

	                }
	            }
	        });

	        // Maintaining insertion order with the help of LinkedList
	        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
	        for (Entry<String, Double> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap;
	    }

	 
	    public static void printMap(Map<String, Double> map)
	    {
	        for (Entry<String, Double> entry : map.entrySet())
	        {
	            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
	        }
	    }
	    
	    
	    public static void printTopXInMap(Map<String, Double> map)
	    {
	    	int currentX = 1;
	    	int numberOfPapers = 0;
	    	int topXAuthors = 0;
	    	int interestedTopXAuthorsInRankingList = 10;
	    	ArrayList<Integer> positionsOfPaper = new ArrayList<Integer>();
	    	ArrayList<String> papersInRankingList = new ArrayList<String>();
	    	ArrayList<String> topXAuthorsInRankingList = new ArrayList<String>();
	    	ArrayList<Integer> positionsOfAuthors = new ArrayList<Integer>();
	        
	      //iterate over a map
	        Iterator<String> it= map.keySet().iterator();
	        while ((it.hasNext()) && (currentX <= topX)) {
	            String keyInMap= it.next();
	            System.out.println("Top " + currentX + " object:   " + "Key : " + keyInMap + " Value : "+ map.get(keyInMap));
	            if (keyInMap.contains("paperrrrrr ")){
	            	numberOfPapers++;
	            	positionsOfPaper.add(currentX);
	            	
	            	papersInRankingList.add(keyInMap.substring(11, keyInMap.length()));
	            }
	            
	            if (!keyInMap.contains("paperrrrrr ") && (topXAuthors < interestedTopXAuthorsInRankingList)){
	            	topXAuthors++;
	            	positionsOfAuthors.add(currentX);
	            	topXAuthorsInRankingList.add(keyInMap);
	            }
	            
	            currentX++;
	        }
	        System.out.println("Number of papers in top " + topX + " ranking list is: " + numberOfPapers);
	        System.out.println("Positions of papers in top " + topX + " ranking list are: " + positionsOfPaper);
	        System.out.println("Papers in ranking list are as follows:");
	        for (String paperTitle : papersInRankingList){
	        	System.out.println(paperTitle);
	        }
	        System.out.println("Positions of authors in top " + interestedTopXAuthorsInRankingList + " ranking list are: " + positionsOfAuthors);
	        System.out.println("Top " + interestedTopXAuthorsInRankingList + " authors in ranking list are as follows:");
	        for (String authorName : topXAuthorsInRankingList){
	        	System.out.println(authorName);
	        }
	    }
	    
}
