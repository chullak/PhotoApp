package com.journaldev.spring.http.client.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.http.client.RestClient;
import com.journaldev.spring.model.Album;

@Component
public class AlbumRestClient implements RestClient<Album> {

	@Inject
	RestTemplate restTemplate;

	@Override
	public List<Album> getList() {

		ResponseEntity<List<Album>> rateResponse = restTemplate.exchange(ALBUMS_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		List<Album> albums = rateResponse.getBody();
		return albums;
	}

}
