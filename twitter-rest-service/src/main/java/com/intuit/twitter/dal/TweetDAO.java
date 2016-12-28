package com.intuit.twitter.dal;


import com.intuit.twitter.entity.Tweet;
import com.intuit.twitter.entity.UserFeedsWrapper;

public interface TweetDAO {
		
	public Tweet createTweet(String userName, String message);
	
	public UserFeedsWrapper getTweets(String userName, int offset, int limit);
}
