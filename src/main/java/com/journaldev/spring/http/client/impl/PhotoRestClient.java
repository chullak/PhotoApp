package com.journaldev.spring.http.client.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.http.client.RestClient;
import com.journaldev.spring.model.Photo;

@Component
public class PhotoRestClient implements RestClient<Photo> {
	@Inject
	RestTemplate restTemplate;

	@Override
	public List<Photo> getList() {

		ResponseEntity<List<Photo>> rateResponse = restTemplate.exchange(PHOTOS_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Photo>>() {
				});
		List<Photo> photo = rateResponse.getBody();
		return photo;
	}

}
