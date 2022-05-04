package com.market.company.controller;

import java.util.Random;

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

import com.market.company.commands.DeleteCompanyCommand;
import com.market.company.commands.RegisterCompanyCommand;
import com.market.company.request.CompanyRegistrationRequest;
import com.market.company.service.CompanyDeletionServiceImpl;
import com.market.company.service.CompanyRegistrationServiceImpl;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyCommandController {

	private Logger log = LoggerFactory.getLogger(CompanyCommandController.class);

	@Autowired
	private CompanyRegistrationServiceImpl companyRegistrationService;

	@Autowired
	private CompanyDeletionServiceImpl companyDeletionService;

	private CommandGateway commandGateway;

	public CompanyCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping(value = "/register")
	public String registerCompany(@Valid @RequestBody CompanyRegistrationRequest companyRegistrationRequest) {
		log.debug("Inside registerCompany() of CompanyCommandController");

		RegisterCompanyCommand registerCompanyCommand = new RegisterCompanyCommand(
				Long.valueOf(new Random().nextLong()), companyRegistrationRequest.getCompanyCode(),
				companyRegistrationRequest.getCompanyName(), companyRegistrationRequest.getCompanyCEO(),
				companyRegistrationRequest.getCompanyTurnOver(), companyRegistrationRequest.getCompanyWebsite(),
				companyRegistrationRequest.getStockExchange());

		try {
			return this.commandGateway.sendAndWait(registerCompanyCommand);
		} catch (Exception exception) {
			throw exception;
		}

		// return
		// companyRegistrationService.registerCompany(companyRegistrationRequest);
	}

	@DeleteMapping(value = "delete/{companyCode}")
	public @ResponseBody String deleteCompanyDetails(
			@NotBlank(message = "Please provide the company code to proceed with Company deletion") @PathVariable String companyCode) {

		DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(Long.valueOf(new Random().nextLong()),
				companyCode);

		log.debug("Inside deleteCompanyDetails() of CompanyCommandController");

		try {
			 return this.commandGateway.sendAndWait(deleteCompanyCommand);
		} catch (Exception e) {
			throw e;
		}

		// return companyDeletionService.deleteCompanyDetails(companyCode);
	}
}
