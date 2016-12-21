import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import twitter4j.GeoLocation;

public class CollectorTest {

	Collector c;
	DateCalculator dc;
	
	@Before
	public void init() {
		c = new Collector();
		dc = new DateCalculator(1);
	}
	
	@Test
	public void testExampleTweets() {
		int count = 0;
		c.setSince(dc.getStartDate());
		c.setUntil(dc.getEndData());
		c.setLocation(new GeoLocation(47.6097, -122.3331));
		try {
			count = c.search("qwerty");
		} catch (LocationNotSelectedExeption e) {
		}
		
		assertNotNull("",c.getExampleTweet());
		
	}
	
	@Test
	public void testResetCollector() {
		int count = 0;
		c.setSince(dc.getStartDate());
		c.setUntil(dc.getEndData());
		c.setLocation(new GeoLocation(47.6097, -122.3331));
		try {
			count = c.search("qwerty");
		} catch (LocationNotSelectedExeption e) {
		}
		c.resetCollector();
		assertEquals("",c.getExampleTweet(),null);
	}
}
