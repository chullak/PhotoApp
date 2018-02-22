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

import com.journaldev.spring.model.Photo;
import com.journaldev.spring.model.User;
import com.journaldev.spring.service.PhotoService;

/**
 * Handles requests for the Employee service.
 */
@Controller

public class PhotoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Inject
	PhotoService photoService;

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Photo> get(@PathVariable("id") int id) {
		logger.info("Fetching Photo with id " + id);
		Photo photo = photoService.findById(id);
		if (photo == null) {
			logger.info("Photo with id " + id + " not found");
			return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Photo photo, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Photo " + photo.getId());

		photoService.save(photo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/ablum/{id}").buildAndExpand(photo.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Photo> update(@PathVariable("id") int id, @RequestBody Photo photo) {
		logger.info("Updating Photo " + id);

		Photo currentphoto = photoService.findById(id);

		if (currentphoto == null) {
			logger.info("Photo with id " + id + " not found");
			return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
		}

		currentphoto.setTitle(photo.getTitle());
		currentphoto.setAlbumId(photo.getAlbumId());
		currentphoto.setThumbnailUrl(photo.getThumbnailUrl());
		currentphoto.setUrl(photo.getUrl());

		photoService.update(currentphoto);
		return new ResponseEntity<Photo>(currentphoto, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Photo> delete(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Photo with id " + id);

		Photo photo = photoService.findById(id);
		if (photo == null) {
			logger.info("Unable to delete. Photo with id " + id + " not found");
			return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
		}
		photoService.delete(photo);

		return new ResponseEntity<Photo>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/album/{id}/photo", method = RequestMethod.GET)
	public ResponseEntity<List<Photo>> listByAlbum(@PathVariable("id") int albumId) {
		List<Photo> photos = photoService.listByAblum(albumId);
		if (photos.isEmpty()) {
			return new ResponseEntity<List<Photo>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
	}

}
