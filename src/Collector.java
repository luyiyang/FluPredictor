
import java.util.ArrayList;
import java.util.Random;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * This class collects data from the twitter API. 
 * This API is not able provide data more than 7 days old</br>
 * Creates a twitter instance form the {@link TwitterConnector} class and then 
 * counts the number of tweets containing some keyword during one time period at a given location 
 * using the search method from {@link Twitter}. </br>
 * Since our goal is to counts several different keywords during the same period at the same location, 
 * I decide not to pass start time, end time and location as parameters of the search method. But store them as 
 * instance variables and set them use mutator method before the search method being called. </br>
 * Setting a location before searching is required in order not to reach the twitter API search rate limit.
 * @author CJC
 *
 */
public class Collector {
	
	private Twitter twitter;
	private String startTime;
	private String endTime;
	private GeoLocation location;
	private ArrayList<Status> exampleTweets;
	private Random rand;
	private boolean isBackup;
	
	/**
	 * Constructor : initialize a collector;
	 */
	public Collector() {
		twitter = TwitterConnector.getTwitter();
		exampleTweets = new ArrayList<>();
		rand = new Random();
		isBackup = false;
	}
	
	/**
	 * clear all the exampleTweets for the last period.
	 * @return this object
	 */
	public Collector resetCollector() {
		exampleTweets.clear();
		return this;
	}
	
	/**
	 * Sets the date the collector starts collecting data. </br>
	 * Tweets sent on this day are counted.
	 * @param startTime the start date in the format "YYYY-MM-DD"
	 * @return this object
	 */
	public Collector setSince(String startTime) {
		this.startTime = startTime;
		return this;
	}
	
	/**
	 * Sets the date the collector stops collecting data. </br>
	 * Tweets sent on this day are NOT counted.
	 * @param endTime the end date in the format "YYYY-MM-DD"
	 * @return this object
	 */
	public Collector setUntil(String endTime){
		this.endTime = endTime;
		return this;
	}
	
	/**
	 * Set the location the collector searches for tweets
	 * @param location the location
	 * @return this object
	 */
	public Collector setLocation(GeoLocation location) {
		this.location = location;
		return this;
	}
	
	/**
	 * get and remove one example tweet from the example tweets list.
	 * @return one tweet {@link Status}
	 */
	public Status getExampleTweet() {
		int bound = exampleTweets.size();
		if (bound <= 0) {
			return null;
		}
		int index = rand.nextInt(bound);
		return exampleTweets.remove(index);
	}
	
	/**
	 * Counts the number of tweet during the time period at given location</br>
	 * Creates a Query object based on the start time, end time and location set before.</br>
	 * Calls search method on twitter object to search for the query and updates the query to next page until all the tweets have been counted.</br> 
	 * Stores all the tweets in a list for further use 
	 * @param keyword the keyword to be counted
	 * @return the keyword count
	 * @throws LocationNotSelectedExeption if the location is not specified
	 */
	public int search(String keyword) throws LocationNotSelectedExeption {
		
		if (location == null) {
			throw new LocationNotSelectedExeption();
		}
		
		Query query = new Query(keyword);
		//searching for tweets within a circle centering at location with a radius of 150 miles
		query.setGeoCode(location, 150, Query.MILES);
		query.setCount(100);
		//start time included, end time excluded
		query.setSince(startTime);
		query.setUntil(endTime);
	
		QueryResult result;
		int count = 0;

		for(;;) {
			
			try {
				
				result = twitter.search(query);
				exampleTweets.addAll(result.getTweets());
				count += result.getTweets().size();
				
				if (!result.hasNext()){
					return count;
				}				
		
				query = result.nextQuery();
				
			} catch (TwitterException e) {
				/*
				 * If anything unexpected happened, try the backup twitter instance first.
				 * If the backup one failed either, terminate the whole program. 
				 */
				if (isBackup){
					System.out.println(e.getMessage());
					System.exit(0);
				}
				isBackup = true;
				twitter = TwitterConnector.getTwitterBackup();
			}
			
		}
	}

}
