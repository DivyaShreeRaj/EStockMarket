package com.market.company.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.request.CompanyRegistrationRequest;
import com.market.company.service.CompanyRegistrationService;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyRegistrationController {

	private Logger log = LoggerFactory.getLogger(CompanyRegistrationController.class);

	@Autowired
	private CompanyRegistrationService companyRegistrationService;

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCompany(
			@Valid @RequestBody CompanyRegistrationRequest companyRegistrationRequest) {
		log.debug("Inside registerCompany() of CompanyRegistrationController");
		return companyRegistrationService.registerCompany(companyRegistrationRequest);
	}
	
}
