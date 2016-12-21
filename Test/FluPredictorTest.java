import static org.junit.Assert.*;

import org.junit.Test;

public class FluPredictorTest {

	@Test
	public void testPredictor() {
		
		FluPredictor fp = new FluPredictor(50.0, 60.0);
		
		assertEquals("Alert should be false", false, fp.alert());
	}
	
	
	@Test
	public void testBondaryConditions() {
		
		FluPredictor fp = new FluPredictor(50.0, 65.0);
		
		assertEquals("Alert should be false", true, fp.alert());
	}
}
