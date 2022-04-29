
package com.market.company.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.response.CompanyInfoResponse;
import com.market.company.service.queries.CompanyInfoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1.0/market/company/")
public class CompanyQueryController {

	private Logger log = LoggerFactory.getLogger(CompanyQueryController.class);

	@Autowired
	private CompanyInfoServiceImpl companyService;

	@Operation(summary = "Get Company details using company code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping(value = "info/{companyCode}")
	public @ResponseBody ResponseEntity<CompanyInfoResponse> getCompanyDetails(
			@Parameter(description = "Company code", example = "Code1") @PathVariable String companyCode) {
		log.debug("Inside getCompanyDetails() of CompanyQueryController");
		return new ResponseEntity<>(companyService.getCompanyDetails(companyCode), HttpStatus.OK);
	}

	@Operation(summary = "Get All the Company details")
	@GetMapping(value = "getall")
	public @ResponseBody ResponseEntity<List<CompanyInfoResponse>> getallCompanyDetails() {
		log.debug("Inside getallCompanyDetails() of CompanyQueryController");
		return new ResponseEntity<>(companyService.getallCompanyDetails(), HttpStatus.OK);
	}

}
