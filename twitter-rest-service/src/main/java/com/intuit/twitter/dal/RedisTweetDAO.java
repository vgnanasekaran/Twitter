package com.intuit.twitter.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.intuit.twitter.entity.Pagination;
import com.intuit.twitter.entity.Tweet;
import com.intuit.twitter.entity.User;
import com.intuit.twitter.entity.UserFeedsWrapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTweetDAO implements TweetDAO {
	static Log log = LogFactory.getLog(RedisUserDAO.class.getName());
	private JedisPool jedisPool = RedisFactory.jedisPool;
	private Gson gson = new Gson();
	UserDAO userDAO = DAOFactory.getUserDAO();
	
	public String getNewTweetId() {
		Jedis redisClient = jedisPool.getResource();
		String tweetId = Long.toString(redisClient.incr("tweet_count"));
		jedisPool.returnResource(redisClient);
		return tweetId;
	}
	
	public Tweet createTweet(String userName, String message) {
		String tweetId = getNewTweetId();
		log.info("tweet Id : " + tweetId);
		String userId = userDAO.getUserIdByUserName(userName);
		Tweet tweetObj = new Tweet(userId, tweetId, message);
		Jedis redisClient = jedisPool.getResource();
		redisClient.set("tweetId_" + tweetId, gson.toJson(tweetObj));
		jedisPool.returnResource(redisClient);
		log.info("Created Tweet ID : " + tweetId + " Successfully");
		addTweetIdToFollowers(userId, tweetId);
		return tweetObj;
	}
	
	private void addTweetIdToFollowers(String userId, String tweetId) {
		User userObj = userDAO.getUserById(userId);
		Jedis redisClient = jedisPool.getResource();
		for (String followerId : userObj.getFollowers()) {
			redisClient.lpush("tweetIdsForUserId_" +followerId, tweetId);
			List<String> tweetIds = redisClient.lrange("tweetIdsForUserId_" +followerId, 0, -1);
			log.info("tweetIdsForUserId_" +followerId + ": " + tweetIds);
		}
		jedisPool.returnResource(redisClient);		
	}

	public UserFeedsWrapper getTweets(String userName, int offset, int limit) {
		User usrObj = userDAO.getUserByName(userName);
		String userId = usrObj.getUserId();
		Jedis redisClient = jedisPool.getResource();

		List<String> tweetIds = redisClient.lrange("tweetIdsForUserId_"+userId, offset, limit);
		List<Tweet> tweets = new ArrayList<Tweet>();	
		
		for (String tweetId : tweetIds) {
			String tweetJsonObj = redisClient.get("tweetId_" + tweetId);
			Tweet tweetObj = gson.fromJson(tweetJsonObj, Tweet.class);
			tweets.add(tweetObj);
		}
		List<String> allTweetIds = redisClient.lrange("tweetIdsForUserId_"+userId, 0, -1);
		int totalTweetCount = allTweetIds.size();		
		Pagination pageInfo = new Pagination(offset, limit, totalTweetCount);
		UserFeedsWrapper userFeedsWrapObj = new UserFeedsWrapper(usrObj, tweets, pageInfo);
		jedisPool.returnResource(redisClient);	
		return userFeedsWrapObj;
	}
	
}
