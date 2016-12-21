import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class KeywordsGetterTest {
	
	private KeywordsGetter kg;
	
	@Before
	public void setUp() throws FileNotFoundException{
		kg = new KeywordsGetter("keywords.csv");
	}
	
	
	@Test
	public void testWeight() {
		KeywordsGetter kg;
		try {
			kg = new KeywordsGetter("keywords.csv");
			
			double weight = kg.getKeywordWeight("flu symptoms");
			assertEquals("flu symptoms should be 0.35",0.35, weight, 0.001);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testGetSize(){
		int size = kg.getKeywordsSize();
		assertEquals("Size should be",5, size);
	}
	


}
