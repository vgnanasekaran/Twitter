package com.intuit.twitter.dal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.intuit.twitter.entity.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUserDAO implements UserDAO {
	static Log log = LogFactory.getLog(RedisUserDAO.class.getName());
	private JedisPool jedisPool = RedisFactory.jedisPool;
	private Gson gson = new Gson();

	public String getNewUserId() {
		Jedis redisClient = RedisFactory.jedisPool.getResource();
		String userId = Long.toString(redisClient.incr("user_count"));
		jedisPool.returnResource(redisClient);
		return userId;
	}

	public String getFollowers(String userName) {
		Jedis redisClient = jedisPool.getResource();
		String follower_ids = redisClient.getrange(userName + "_followers", 0, -1);
		jedisPool.returnResource(redisClient);
		return follower_ids;
	}

	public User getUserByName(String userName) {
		String userId = getUserIdByUserName(userName);
		return getUserById(userId);
	}

	public User addUser(String userName) {
		String userId = getNewUserId();
		log.info("User Id : " + userId);
		User newUser = new User(userId, userName);
		Jedis redisClient = jedisPool.getResource();
		redisClient.set("userName_" + userName, userId);
		redisClient.set("userId_" + userId, gson.toJson(newUser));
		jedisPool.returnResource(redisClient);
		log.info("Before Returning new user : " + newUser);

		return newUser;
	}
	
	public String getUserIdByUserName(String userName){
		Jedis redisClient = jedisPool.getResource();
		String userId = redisClient.get("userName_" + userName);
		jedisPool.returnResource(redisClient);
		return userId;
	}
	
	public User getUserById(String userId) {
		Jedis redisClient = jedisPool.getResource();
		String jsonUserObj = redisClient.get("userId_" + userId);
		User userObj = gson.fromJson(jsonUserObj, User.class);
		jedisPool.returnResource(redisClient);
		return userObj;
	}
	
	public void addFollower(String userName, String followUserName) {
		User followUserObj = getUserByName(followUserName);
		String userId = getUserIdByUserName(userName);
		followUserObj.addFollower(userId);
		Jedis redisClient = jedisPool.getResource();
		redisClient.set("userId_" + followUserObj.getUserId(), gson.toJson(followUserObj));
		jedisPool.returnResource(redisClient);
	}
	
}
