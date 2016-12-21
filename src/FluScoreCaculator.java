import java.util.ArrayList;

/**
 * This class will calculate the flu score given the start time and end time of a period
 * @author LuyiYang
 *
 */
public class FluScoreCaculator {
	
	/**
	 * This is the instance variable
	 */
	private KeywordsGetter keywordsGetter;
	
	/**
	 * This is the constructor
	 * @param keywordsGetter, it will take in the keywordsGetter as a parameter
	 */
	public FluScoreCaculator(KeywordsGetter keywordsGetter){
		this.keywordsGetter = keywordsGetter;
	}
	
	/**
	 * This method will calculate the flu score 
	 * The flu score is calculated by adding each key word's count times the keyword's weight
	 * @param counts, each keyword's counts
	 * @param keywords, an ArrayList of keywords
	 * @return
	 */
	public double getfluScore(ArrayList<Integer> counts, ArrayList<String> keywords){

		double fluScore = 0;

		for(int i = 0; i<counts.size(); i++){
			
			fluScore += counts.get(i)*keywordsGetter.getKeywordWeight(keywords.get(i));

		}

		return fluScore;
	}

}
