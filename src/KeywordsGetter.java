import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;

/**
 * This class will read a file, parse it and get the keywords and their relative weights
 * @author LuyiYang
 *
 */
public class KeywordsGetter {
	
	
	/**
	 * These are instance variables
	 */
	private HashMap<String,Double> keywordsmap;
	private String path;
	
	/**
	 * This is the constructor of the file
	 * @param path, the file name that will be read and parsed.
	 * @throws FileNotFoundException
	 */
	public KeywordsGetter(String path) throws FileNotFoundException {
		
		this.path = path;
		keywordsmap = new HashMap<>();
		mapInit();
		
	}
	
	/**
	 * This is the method to parse the file and put relevant information into a hashmap
	 * @throws FileNotFoundException
	 */
	private void mapInit() throws FileNotFoundException{
		FileReader in = new FileReader(path);
		String line;
		String[] temp;
		
		while (in.hasNextLine()) {
			line = in.nextLine();
			temp = line.split(",");
			keywordsmap.put(temp[0],Double.parseDouble(temp[1]));
		}
	}
	
	/**
	 * This method will return the value, which is weight, given the the key
	 * @param keyword, the key of the hashmap
	 * @return
	 */
	public Double getKeywordWeight(String keyword) {
		return keywordsmap.get(keyword);
	}
	
	/**
	 * This method will get the keyset of the hashmap, which is the keywords
	 * @return the keywords' set
	 */
	public Set<String> getKeywords(){
		return keywordsmap.keySet();	
	}
	
	/**
	 * This method will get the size of the hashmap
	 * @return the size of the hashmap
	 */
	public int getKeywordsSize(){
		return keywordsmap.keySet().size();	
	}
	

}
