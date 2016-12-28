package com.intuit.twitter.entity;

import java.util.ArrayList;
import java.util.List;

public class UserFeedsWrapper {
	public User usrObj;
	public List<Tweet> tweets = new ArrayList<Tweet>();
	public Pagination pageInfo;
	
	public UserFeedsWrapper(User usrObj, List<Tweet> tweets, Pagination pageInfo) {
		this.usrObj = usrObj;
		this.tweets = tweets;
		this.pageInfo = pageInfo;
	}

	public User getUsrObj() {
		return usrObj;
	}

	public void setUsrObj(User usrObj) {
		this.usrObj = usrObj;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
}
