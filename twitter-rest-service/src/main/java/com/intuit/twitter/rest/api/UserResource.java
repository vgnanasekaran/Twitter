package com.intuit.twitter.rest.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.twitter.dal.DAOFactory;

import com.intuit.twitter.dal.UserDAO;
import com.intuit.twitter.entity.User;

@RestController
public class UserResource {
	UserDAO usrdao = DAOFactory.getUserDAO();

	@RequestMapping(value = "/user",method = RequestMethod.POST)
	User  addUser(@RequestParam(value = "userName") String userName){
		return usrdao.addUser(userName);
	}

	@RequestMapping(value = "/user",method = RequestMethod.GET)
	User  getUser(@RequestParam(value = "userName") String userName){
		return usrdao.getUserByName(userName);
	}
	
	@RequestMapping(value = "/follow",method = RequestMethod.POST)
	void  addFollower(@RequestParam(value = "userName") String userName,@RequestParam(value = "followUserName") String followUserName){
		 usrdao.addFollower(userName, followUserName);
	}	
	
	
}
