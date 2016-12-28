package com.intuit.twitter.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
		Object[] sources = { Application.class, com.intuit.twitter.rest.api.TweetResource.class,
				com.intuit.twitter.rest.api.UserResource.class };
		SpringApplication.run(sources, args);

    }
}
