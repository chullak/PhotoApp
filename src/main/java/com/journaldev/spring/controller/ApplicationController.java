package com.journaldev.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.service.ApplicationService;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class ApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Inject
	ApplicationService applicationService;

	@RequestMapping(value = RestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody String getDummyEmployee() {
		System.out.println("Started");
		applicationService.refresh();
		return "Sucess";
	}

}
