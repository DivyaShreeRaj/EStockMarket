package com.market.company.controller;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.service.CompanyDeletionService;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyDeletionController {

	private Logger log = LoggerFactory.getLogger(CompanyDeletionController.class);

	@Autowired
	private CompanyDeletionService companyDeletionService;

	@DeleteMapping(value = "delete/{companyCode}")
	public @ResponseBody ResponseEntity<String> deleteCompanyDetails(
			@NotBlank(message = "Please provide the company code to proceed with Company deletion") @PathVariable String companyCode) {

		log.debug("Inside deleteCompanyDetails() of CompanyDeletionController");
		return companyDeletionService.deleteCompanyDetails(companyCode);
	}
}