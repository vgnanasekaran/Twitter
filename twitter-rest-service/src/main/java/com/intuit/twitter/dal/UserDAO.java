package com.intuit.twitter.dal;

import com.intuit.twitter.entity.User;

public interface UserDAO {

	public User addUser(String userName);
	
	public User getUserByName(String userName);

	public User getUserById(String userId);

	public void addFollower(String userName, String followerName);
	
	public String getUserIdByUserName(String userName);
	
}
