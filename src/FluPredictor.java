import java.util.ArrayList;
/**
 * This class will predict flu, given the flu score of the first period and the second period
 * @author LuyiYang
 *
 */
public class FluPredictor {
	
	/**
	 * These are instance variables
	 */
	private double fsBefore;
	private double fsNow;
	
	/**
	 * This is the constructor
	 * @param firstPeriodFS, the flu score of the first period
	 * @param secondPeriodFS, the flu score of the second period
	 */
	public FluPredictor(double firstPeriodFS, double secondPeriodFS) {
		fsBefore = firstPeriodFS;
		fsNow = secondPeriodFS;
	}
	
	/**
	 * This method will give the flu alert is there is a flu
	 * Note: if the flu score increased over 30%, there will be a flu; otherwise, not.
	 * @return true if there will be a flu; false if there will not be a flu
	 */
	public boolean alert(){
		
		double increaseRate = 0;
		
		increaseRate = (fsNow - fsBefore)/fsBefore;
		
		if(increaseRate >= 0.3){
			
			return true;
			
		}else{
			
			return false;
		}
		
	}
	

	
}

