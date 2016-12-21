import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class FluScoreCaculatorTest {

	@Test
	public void testFluScore() {
		
		KeywordsGetter kg;
		
		try {
			kg = new KeywordsGetter("keywords.csv");
			
			FluScoreCaculator fsc = new FluScoreCaculator(kg);
			
			ArrayList<Integer> counts = new ArrayList<Integer>();
			counts.add(100);
			counts.add(100);
			counts.add(100);
			counts.add(100);
			counts.add(100);
			
			ArrayList<String> keywords = new ArrayList<String>();
			
			for(String keyword: kg.getKeywords()){
				keywords.add(keyword);
			}
			
			
			double fluScore = fsc.getfluScore(counts, keywords);
			
			assertEquals("Flu score should be 100", 100.0, fluScore, 0.01);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
	}

}
