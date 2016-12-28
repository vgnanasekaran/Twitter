package com.intuit.twitter.entity;
import java.time.Instant;

public class Tweet {

	    private final String userId;
	    private final String tweetId;
	    private final String content;
	    private final Instant tweetTimeInUTC;

	    public Tweet(String userId, String tweetId, String content) {
	        this.userId = userId;
	        this.tweetId = tweetId;
	        this.content = content;
	        this.tweetTimeInUTC = Instant.now();
	    }

	    public String getTweetId() {
	    	return tweetId;
	    }
	    
	    public String getUserId() {
	        return userId;
	    }

	    public String getContent() {
	        return content;
	    }
	    
	    public String getTweetTimeInUTC() {
	    	return tweetTimeInUTC.toString();
	    }

}
