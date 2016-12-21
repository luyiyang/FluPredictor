import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import twitter4j.GeoLocation;
import twitter4j.Status;
/**
 * This class is responsible for all functionalities (public methods) that interact with JavaFX
 * @author LuyiYang
 *
 */
public class Functions {

	private LocationGetter lg;
	private KeywordsGetter kg;
	private GeoLocation location;
	private Collector c; 
	private ArrayList<HashMap<String, Integer>> keywordsCounts;
	
	/**
	 * This is the constructor
	 */
	public Functions(){
		try {
			lg = new LocationGetter("Locations.csv");
			kg = new KeywordsGetter("keywords.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		c = new Collector();
		keywordsCounts = new ArrayList<HashMap<String, Integer>>();
		
	}
	
	/**
	 * This method will get the set of States
	 * @return a Set<String> of States
	 */
	public Set<String> getStates(){
		return lg.getStates();
	}
	
	/**
	 * This is the setter method of location
	 * It will set the parameter's GeoLocation to the instance variable location
	 * @param state name 
	 */
	public void setLocation(String state){
		
		location = lg.getLocation(state);
	}
	
	/**
	 * This is the getter method of a state
	 * @param state name
	 * @return GeoLocation of the state
	 */
	public GeoLocation getLocation(String state) {
		return lg.getLocation(state);
	}
	
	public GeoLocation getGeoLocation(){
		return location;
	}
	
	/**
	 * This method will decide whether to send a flu alert given the area's location and time
	 * @return true if there will be an flu; false otherwise.
	 * @throws LocationNotSelectedExeption
	 */
	public boolean alert() throws LocationNotSelectedExeption{
		
		keywordsCounts.clear();
		
		FluScoreCaculator f = new FluScoreCaculator(kg);
		//Collector c = new Collector();
		DateCalculator d = new DateCalculator(3);
		c.resetCollector();
		
		ArrayList<Integer> counts = new ArrayList<Integer>();
		ArrayList<String> keywords = new ArrayList<String>();
		
		int numberOfPeriods = 2;
		
		double[] fluScoreArray = new double[numberOfPeriods];

		c.setLocation(location);

		for(int i = 1; i<=numberOfPeriods; i++){
			
			HashMap<String, Integer> k = new HashMap<String, Integer>();
			
			c.setSince(d.getStartDate());
			c.setUntil(d.getEndData());
			
			counts.clear();
			keywords.clear();
			
			for(String key: kg.getKeywords()){
				int count = c.search(key); 
				counts.add(count);
				keywords.add(key);
				k.put(key, count);
			}
			
			keywordsCounts.add(k);
			
			fluScoreArray[i-1] = f.getfluScore(counts, keywords);

			d.moveBackward();
		}


		FluPredictor fp = new FluPredictor(fluScoreArray[1], fluScoreArray[0]);

		return fp.alert();
		
	}

	/**
	 * This is the getter method of keywordsCounts
	 * @return ArrayList<HashMap<String, Integer>> 
	 */
	public ArrayList<HashMap<String, Integer>> getkeyWordsCounts(){
		return keywordsCounts;
		
	}

	
	
}
