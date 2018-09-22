import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class DiggInputUserCommentStory {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String userKeysFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\DiggTwitter\\Digg\\user_keys_v1.m";
		String userStoryCKTnFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\DiggTwitter\\Digg\\user_comment_story_v1.m";
		
		String outputFilePath = "E:\\TopKNodesIdentificationInHetergeneousNetworks\\DiggTwitter\\Digg\\DiggUserCommentStory-meta.txt";
		
		
		Integer userKeyIndex = 0;
		Hashtable<String, String> userKeys = new Hashtable<String, String>();
		Hashtable<String, ArrayList<String>> storyUsers = new Hashtable<String, ArrayList<String>>();
				
		FileReader fr = new FileReader(userKeysFilePath);
		BufferedReader br = new BufferedReader(fr);
		
		try{
			String s; 
			while((s = br.readLine()) != null) {
				userKeyIndex++;
				userKeys.put(userKeyIndex.toString(), s.trim());				
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Total number of users: " + userKeyIndex);
		
		FileReader frOne = new FileReader(userStoryCKTnFilePath);
		BufferedReader brOne = new BufferedReader(frOne);
		
		int numberOfUnfoundUsers = 0;
		int numberOfUnindexedStories = 0;
		
		try{
			String sOne; 
			while((sOne = brOne.readLine()) != null) {
				String[] singleLineContents;
				String currentUser = "";
				
				singleLineContents = sOne.split(" ");
				String currentUserStr = singleLineContents[0].trim();
				if (userKeys.containsKey(currentUserStr)){
					currentUser = userKeys.get(currentUserStr);
				}else{
					System.out.println(currentUserStr);
					numberOfUnfoundUsers++;
				}
				String currentStory = singleLineContents[1];
				if (Integer.parseInt(currentStory) > 44005){
					numberOfUnindexedStories++;
				}
				if (!storyUsers.containsKey(currentStory)){
					ArrayList<String> existingUsers = new ArrayList<String>();
					existingUsers.add(currentUser);
					storyUsers.put(currentStory, existingUsers);
				}else{
					ArrayList<String> currentExistingUsers = new ArrayList<String>();
					currentExistingUsers = storyUsers.get(currentStory);
					if (!currentExistingUsers.contains(currentUser)){
						currentExistingUsers.add(currentUser);
						storyUsers.replace(currentStory, currentExistingUsers);
					}
				}
				
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Number of unfound users: " + numberOfUnfoundUsers);
		System.out.println("Number of unindexed stories: " + numberOfUnindexedStories);
		
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){
			try {
				Enumeration<String> en = storyUsers.keys();
				while (en.hasMoreElements()) {
					String currentStoryOne = en.nextElement();
					writer.write("id = {" + currentStoryOne + "}");
					writer.newLine();
					String usersOfAStoryStr = "";
					ArrayList<String> usersOfAStory = storyUsers.get(currentStoryOne);
					for (int i = 0; i < usersOfAStory.size(); i++){
						if (i != usersOfAStory.size()-1){
							usersOfAStoryStr = usersOfAStoryStr + usersOfAStory.get(i) + "; ";
						}else{
							usersOfAStoryStr = usersOfAStoryStr + usersOfAStory.get(i);
						}
					}
					writer.write("author = {" + usersOfAStoryStr + "}");
					writer.newLine();
					writer.write("title = {" + currentStoryOne + "}");
					writer.newLine();
					writer.newLine();
					
				}
				}catch (Exception e) {
					throw e;
				}
		}catch (IOException e) {
			//System.out.println("Exception");
		}
		
		finally {
			fr.close();
			br.close();
		}
	}

}
