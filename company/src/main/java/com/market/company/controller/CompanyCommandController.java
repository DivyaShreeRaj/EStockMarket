package com.market.company.controller;

import java.util.Random;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1.0/market/company")
@Tag(name = "Company Commands", description = "This is a controller for command operations on Company Resource")
public class CompanyCommandController {

	private Logger log = LoggerFactory.getLogger(CompanyCommandController.class);

	private CommandGateway commandGateway;

	public CompanyCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping(value = "/register")
	@Operation(summary = "Register new Company details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	public String registerCompany(@RequestBody @Valid CompanyRegistrationRequest companyRegistrationRequest) {
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
	@Operation(summary = "Delete Company details using company code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })

	public @ResponseBody String deleteCompanyDetails(
			@Parameter(description = "Company code", example = "Code1") @NotBlank(message = "Please provide the company code to proceed with Company deletion") @PathVariable String companyCode) {

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
