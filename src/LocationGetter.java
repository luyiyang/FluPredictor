import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import twitter4j.GeoLocation;
/**
 * Reads and parses the file containing location information -- its name and its latitude and longitude. </p>
 * Once this object is initialized, user could search a string for its location, or get all the string this object stored 
 * @author CJC
 *
 */
public class LocationGetter {

	private HashMap<String,GeoLocation> map;
	private String path;
	
	/**
	 * Constructor: initialize this object, map a coordinate to a string based on the input file
	 * @param path where the file is
	 * @throws FileNotFoundException
	 */
	public LocationGetter(String path) throws FileNotFoundException {
		
		this.path = path;
		map = new HashMap<>();
		mapInit();
		
	}
	
	/**
	 * Creates a hash map from a string to its coordinate by parsing the file.</p>
	 * the input file must be in the format "Name, latitude, longitude" for each line
	 * @throws FileNotFoundException
	 */
	private void mapInit() throws FileNotFoundException{
		FileReader in = new FileReader(path);
		String line;
		String[] temp;
		GeoLocation location;
		
		while (in.hasNextLine()) {
			line = in.nextLine();
			temp = line.split(",");
			location = new GeoLocation(Double.parseDouble(temp[1]),Double.parseDouble(temp[2]));
			map.put(temp[0], location);
		}
	}
	
	/**
	 * get the location of one state
	 * @param state the name string of the state, must be exact the same as its name in the input file.
	 *  (full spelling and capitalized initial)
	 * @return its location
	 */
	public GeoLocation getLocation(String state) {
		return map.get(state);
	}
	
	/**
	 * get all the states' name strings
	 * @return a sorted String set of all States' name
	 */
	public Set<String> getStates(){
		return new TreeSet<String>(map.keySet());	
	}
}
