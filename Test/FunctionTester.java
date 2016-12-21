import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import twitter4j.GeoLocation;

public class FunctionTester {
	
	private Functions f;
	
	@Before
	public void setup(){
		f = new Functions();
	}
	
	@Test
	public void testFunctionNotNull() {
		Functions newFunction = new Functions();

		assertNotNull("Card creation cannot be null", newFunction);
	}
	
	

	
	@Test
	public void testGetStates(){
		
		int a = f.getStates().size();
		int b = 0;
		try {
			LocationGetter lg = new LocationGetter("Locations.csv");
			b = lg.getStates().size();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertEquals("a and b should be equal",b,a);
		
	}
	
	@Test
	public void testSetAndGetLocation(){
		
		boolean success = false;
		
		f.setLocation("New York");
		
		GeoLocation gl = f.getLocation("New York");
		
		if(gl == f.getGeoLocation()){
			
			success = true;
			
		}
		
		assertEquals("success should be true to indicate that the two methods work well", true, success);
	
	}
	

}


