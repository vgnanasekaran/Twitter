package com.intuit.twitter.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String userName;
    private List<String> followerIds = new ArrayList<String>();


	public User(String userId, String userName) {
    	this.userId = userId;
    	this.userName = userName;
    }	

    public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	
	public List<String> getFollowers() {
		return followerIds;
	}
	
	public void addFollower(String followerId) {
		followerIds.add(followerId);
	}

}
