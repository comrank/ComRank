import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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


public class TopPageRankXObjects {
	
	public static boolean DESC = false;
	public static int topX = 10;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		
    	String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\V1DMTesting16May\\PageRankOutput.txt";
    	
    	//String inputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\InputDatasets\\RealWorldToyDatasetTesting\\PageRankOutput.txt";

		FileReader fr = new FileReader(inputFilePath);
		BufferedReader br = new BufferedReader(fr);
		
		String valueContent;
		
		Map<String, Double> unsortMap = new HashMap<String, Double>();
				
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
					//System.out.println(objectName);
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
			//System.out.println("Exception");
		}
		
		finally {
			fr.close();
			br.close();
		}

	    System.out.println("Before sorting......");
	    printMap(unsortMap);
	    
        System.out.println("After sorting descindeng order......");
        Map<String, Double> sortedMapDesc = sortByComparator(unsortMap, DESC);
        printMap(sortedMapDesc);
        
        System.out.println("Generating X objects with top page rank values......");
        Map<String, Double> sortedMapDescTopOjbects = sortByComparator(unsortMap, DESC);
        printTopXInMap(sortedMapDescTopOjbects);
	        
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
	        
	      //iterate over a map
	        Iterator<String> it= map.keySet().iterator();
	        while ((it.hasNext()) && (currentX <= topX)) {
	            String keyInMap= it.next();
	            System.out.println("Top " + currentX + " object:   " + "Key : " + keyInMap + " Value : "+ map.get(keyInMap));
	            currentX++;
	        }
	    }
}
