package com.market.company.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.commands.RegisterCompanyCommand;
import com.market.company.request.CompanyRegistrationRequest;
import com.market.company.service.commands.CompanyDeletionServiceImpl;
import com.market.company.service.commands.CompanyRegistrationServiceImpl;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyCommandController {

	private Logger log = LoggerFactory.getLogger(CompanyCommandController.class);

	@Autowired
	private CompanyRegistrationServiceImpl companyRegistrationService;

	@Autowired
	private CompanyDeletionServiceImpl companyDeletionService;

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCompany(
			@Valid @RequestBody CompanyRegistrationRequest companyRegistrationRequest) {
		log.debug("Inside registerCompany() of CompanyCommandController");

		RegisterCompanyCommand registerCompanyCommand = new RegisterCompanyCommand(
				companyRegistrationRequest.getCompanyCode(), companyRegistrationRequest.getCompanyName(),
				companyRegistrationRequest.getCompanyCEO(), companyRegistrationRequest.getCompanyTurnOver(),
				companyRegistrationRequest.getCompanyWebsite(), companyRegistrationRequest.getStockExchange());

		try {
			return commandGateway.sendAndWait(registerCompanyCommand);
		} catch (Exception exception) {
			throw exception;
		}

		// return
		// companyRegistrationService.registerCompany(companyRegistrationRequest);
	}

	@DeleteMapping(value = "delete/{companyCode}")
	public @ResponseBody ResponseEntity<String> deleteCompanyDetails(
			@NotBlank(message = "Please provide the company code to proceed with Company deletion") @PathVariable String companyCode) {

		log.debug("Inside deleteCompanyDetails() of CompanyCommandController");
		return companyDeletionService.deleteCompanyDetails(companyCode);
	}
}
