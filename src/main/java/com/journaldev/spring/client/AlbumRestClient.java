package com.journaldev.spring.client;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.model.Album;

@Component
public class AlbumRestClient {
	private static final String HTTPS_ABBUMS_URI = "https://jsonplaceholder.typicode.com/albums";

	@Inject
	RestTemplate restTemplate;

	public List<Album> getPhoto() {

		ResponseEntity<List<Album>> rateResponse = restTemplate.exchange(HTTPS_ABBUMS_URI, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		List<Album> albums = rateResponse.getBody();
		return albums;
	}

}
