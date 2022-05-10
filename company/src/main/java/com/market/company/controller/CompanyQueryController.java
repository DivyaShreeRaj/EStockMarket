
package com.market.company.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.query.GetAllCompanyQuery;
import com.market.company.query.GetByCompanyCodeQuery;
import com.market.company.response.CompanyInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * CompanyQueryController
 * 
 * @author User
 *
 */
@RestController
@RequestMapping(value = "/api/v1.0/market/company/")
@Tag(name = "Company Query", description = "This is a controller for query operations on Company Resource")
public class CompanyQueryController {

	private Logger log = LoggerFactory.getLogger(CompanyQueryController.class);

	@Autowired
	QueryGateway queryGateway;

	/**
	 * getCompanyDetails
	 * 
	 * @param companyCode companyCode
	 * @return CompanyInfoResponse
	 */
	@Operation(summary = "Get Company details using company code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping(value = "info/{companyCode}")
	public @ResponseBody CompanyInfoResponse getCompanyDetails(
			@Parameter(description = "Company code", example = "Code1") @PathVariable String companyCode) {
		log.debug("Inside getCompanyDetails() of CompanyQueryController");

		GetByCompanyCodeQuery getByCompanyCodeQuery = new GetByCompanyCodeQuery(companyCode);

		return queryGateway.query(getByCompanyCodeQuery, ResponseTypes.instanceOf(CompanyInfoResponse.class)).join();

		// return new ResponseEntity<>(companyService.getCompanyDetails(companyCode),
		// HttpStatus.OK);
	}

	/**
	 * getallCompanyDetails
	 * 
	 * @return List<CompanyInfoResponse>
	 */
	@Operation(summary = "Get All the Company details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })

	@GetMapping(value = "getall")
	public @ResponseBody List<CompanyInfoResponse> getallCompanyDetails() {
		log.debug("Inside getallCompanyDetails() of CompanyQueryController");

		GetAllCompanyQuery getAllCompanyQuery = new GetAllCompanyQuery();

		return queryGateway.query(getAllCompanyQuery, ResponseTypes.multipleInstancesOf(CompanyInfoResponse.class))
				.join();

		// return new ResponseEntity<>(companyService.getallCompanyDetails(),
		// HttpStatus.OK);
	}

}
