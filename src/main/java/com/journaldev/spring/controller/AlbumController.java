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

import com.journaldev.spring.model.Album;
import com.journaldev.spring.service.AlbumService;

/**
 * Handles requests for the Employee service.
 */
@Controller

public class AlbumController {

	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	@Inject
	AlbumService albumService;

	

	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Album> get(@PathVariable("id") int id) {
		logger.info("Fetching Album with id " + id);
		Album album = albumService.findById(id);
		if (album == null) {
			logger.info("Album with id " + id + " not found");
			return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}

	@RequestMapping(value = "/album", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Album album, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Album " + album.getId());

		albumService.save(album);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/ablum/{id}").buildAndExpand(album.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/album/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Album> update(@PathVariable("id") int id, @RequestBody Album album) {
		logger.info("Updating Album " + id);

		Album currentalbum = albumService.findById(id);

		if (currentalbum == null) {
			logger.info("Album with id " + id + " not found");
			return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
		}

		currentalbum.setTitle(album.getTitle());
		currentalbum.setUserId(album.getUserId());
		albumService.update(currentalbum);
		return new ResponseEntity<Album>(currentalbum, HttpStatus.OK);
	}
	
    @RequestMapping(value = "/album/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Album> delete(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting Album with id " + id);
 
        Album album = albumService.findById(id);
        if (album == null) {
            logger.info("Unable to delete. Album with id " + id + " not found");
            return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
        }
        albumService.delete(album);
 
        return new ResponseEntity<Album>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = "/user/{id}/album", method = RequestMethod.GET)
	public ResponseEntity<List<Album>> list(@PathVariable("id") int userId) {
		List<Album> albums = albumService.list(userId);
		if (albums.isEmpty()) {
			return new ResponseEntity<List<Album>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
	}

}
