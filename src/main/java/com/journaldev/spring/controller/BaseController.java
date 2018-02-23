package com.journaldev.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.journaldev.spring.model.ErrorDetails;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorDetails> handleException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails=new ErrorDetails();
		logger.error("Error",ex);
		errorDetails.setErrorDetails(ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
