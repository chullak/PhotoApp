package com.journaldev.spring.client;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.model.Photo;

@Component
public class PhotoRestClient {
	private static final String HTTPS_JSONPLACEHOLDER_TYPICODE_COM_PHOTOS = "https://jsonplaceholder.typicode.com/photos";

	@Inject
	RestTemplate restTemplate;

	public List<Photo> getPhoto() {

		ResponseEntity<List<Photo>> rateResponse = restTemplate.exchange(HTTPS_JSONPLACEHOLDER_TYPICODE_COM_PHOTOS,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Photo>>() {
				});
		List<Photo> photo = rateResponse.getBody();
		return photo;
	}

}
