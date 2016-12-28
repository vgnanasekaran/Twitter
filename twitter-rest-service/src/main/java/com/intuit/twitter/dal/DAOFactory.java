package com.intuit.twitter.dal;

public class DAOFactory {
	private static RedisUserDAO redisUserDAO = new RedisUserDAO();
	private static RedisTweetDAO redisTweetDAO = new RedisTweetDAO();
	
	public static UserDAO getUserDAO() {
		return redisUserDAO;
	}

	public static RedisTweetDAO getTweetDAO() {
		return redisTweetDAO;
	}
	
}
