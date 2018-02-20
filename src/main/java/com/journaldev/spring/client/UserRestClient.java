package com.journaldev.spring.client;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.model.User;

@Component
public class UserRestClient {
	private static final String HTTPS_JSONPLACEHOLDER_TYPICODE_COM_PHOTOS = "https://jsonplaceholder.typicode.com/users";

	@Inject
	RestTemplate restTemplate;

	public List<User> getPhoto() {

		ResponseEntity<List<User>> rateResponse = restTemplate.exchange(HTTPS_JSONPLACEHOLDER_TYPICODE_COM_PHOTOS,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
				});
		List<User> users = rateResponse.getBody();
		return users;
	}

}
