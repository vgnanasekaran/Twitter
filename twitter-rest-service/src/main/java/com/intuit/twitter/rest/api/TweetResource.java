package com.intuit.twitter.rest.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.twitter.dal.DAOFactory;
import com.intuit.twitter.dal.TweetDAO;
import com.intuit.twitter.entity.Tweet;
import com.intuit.twitter.entity.UserFeedsWrapper;



@RestController
public class TweetResource {
	TweetDAO tweetDAO = DAOFactory.getTweetDAO();
	
	@RequestMapping(value = "/feeds",method = RequestMethod.GET)
	UserFeedsWrapper getTweets(@RequestParam(value = "userName") String userName, @RequestParam(value = "offset", defaultValue="0" ) int offset, @RequestParam(value = "limit", defaultValue="100") int limit ) {
		   return tweetDAO.getTweets(userName, offset, limit);
	}
	
	@RequestMapping(value = "/tweet",method = RequestMethod.POST)
	Tweet addTweet(@RequestParam(value = "userName") String userName, @RequestParam(value = "message") String message) {

		return tweetDAO.createTweet(userName, message);
	}

	
}
