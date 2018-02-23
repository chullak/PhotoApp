package com.journaldev.spring.http.client.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.http.client.RestClient;
import com.journaldev.spring.model.User;

@Component
public class UserRestClient implements RestClient<User>{

	@Inject
	RestTemplate restTemplate;

	@Override
	public List<User> getList() {

		ResponseEntity<List<User>> rateResponse = restTemplate.exchange(USER_URL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
				});
		List<User> users = rateResponse.getBody();
		return users;
	}

}
