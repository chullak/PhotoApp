package com.journaldev.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.journaldev.spring.model.User;
import com.journaldev.spring.model.User;
import com.journaldev.spring.service.UserService;

/**
 * Handles requests for the Employee service.
 */
@Controller

public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	UserService userService;

	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> list() {
		List<User> users = userService.list();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@PathVariable("id") int id) {
		logger.info("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			logger.info("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User " + user.getId());

		userService.save(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/ablum/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User user) {
		logger.info("Updating User " + id);

		User currentuser = userService.findById(id);

		if (currentuser == null) {
			logger.info("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		user.setId(id);
		userService.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            logger.info("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    
}
