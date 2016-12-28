package com.intuit.twitter.dal;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisPool;


public class RedisFactory {
	public static JedisPool jedisPool;
	
	static{
		ClassPathXmlApplicationContext context
               = new ClassPathXmlApplicationContext("redis-pool-config.xml");
        jedisPool  = (JedisPool) context.getBean("jedisPool");
 	}
	
}
