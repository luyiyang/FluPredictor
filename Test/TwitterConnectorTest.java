import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterConnectorTest {

	Query q;
	Twitter tw1,tw2;
	QueryResult r1,r2;
	List<Status> t1,t2;

	@Before
	public void QueryCreate(){

		q = new Query("Trump");
		q.setCount(2);
		DateCalculator dc = new DateCalculator(2);
		q.setUntil(dc.getStartDate());
	}
	
	@Test 
	public void testGettingTwitterInstance() {

		tw1 =  TwitterConnector.getTwitter();
		tw2 =  TwitterConnector.getTwitterBackup();
		assertNotNull(tw1);
		assertNotNull(tw2);
	}
	
	@Test
	public void test1() {
	//using different twitter instance searching for the same query should return the same result	
		tw1 =  TwitterConnector.getTwitter();
		tw2 =  TwitterConnector.getTwitterBackup();
		
		try {
			r1 = tw1.search(q);
			r2 = tw2.search(q);
		} catch (TwitterException e) {

		}
		t1 = r1.getTweets();
		t2 = r2.getTweets();
		
		for (int  i = 0 ; i < t1.size() ; i++) {
			assertEquals("same query searched by different twitter token should return the same result",t1.get(i).getText(),t2.get(i).getText());
		}

	}
	
	@Test
	public void test2() {
	//calling nextQuery() on different QueryResult should return the same query.
		tw1 =  TwitterConnector.getTwitter();
		tw2 =  TwitterConnector.getTwitterBackup();
		
		try {
			r1 = tw1.search(q);
			r2 = tw2.search(q);
			
			Query q1 = r1.nextQuery();
			Query q2 = r2.nextQuery();
			
			t1 = tw1.search(q1).getTweets();
			t2 = tw1.search(q2).getTweets();
			// from test1 we know using tw1 or tw2 searching for q2 will have the same result
			
		} catch (TwitterException e) {

		}
		
		for (int  i = 0 ; i < t1.size() ; i++) {
			assertEquals(t1.get(i).getText(),t2.get(i).getText());
		}
		
	}
	
	@Test
	public void test3() {
	//changing twitter instance in the middle of search should not effect the final result 
		tw1 =  TwitterConnector.getTwitter();
		tw2 =  TwitterConnector.getTwitter();
		
		try {
			r1 = tw1.search(q);
			r2 = tw2.search(q);
			t1 = r1.getTweets();
			t2 = r2.getTweets();
			
			tw2 = TwitterConnector.getTwitterBackup();
			q  = r1.nextQuery(); 
			// from test2 we know r1.nextQuery has the same search result with r2.nextQuery

			t1.addAll(tw1.search(q).getTweets());
			t2.addAll(tw2.search(q).getTweets());
			
			Query q1 = r1.nextQuery();
			Query q2 = r2.nextQuery();
			
			t1.addAll(tw1.search(q1).getTweets());
			t2.addAll(tw1.search(q2).getTweets());
			
		} catch (TwitterException e) {

		}
		
		for (int  i = 0 ; i < t1.size() ; i++) {
			assertEquals(t1.get(i).getText(),t2.get(i).getText());
		}
		
	}
	
}
