package com.market.stock.controller;

import java.sql.Date;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.stock.query.GetStocksQuery;
import com.market.stock.response.StockMongoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1.0/market/stock/")
@Tag(name = "Stock Query", description = "This is a controller for query operations on Stock Resource")
public class StockQueryController {

	@Autowired
	QueryGateway queryGateway;

	@Operation(summary = "Get All Stocks By Date")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping(value = "get/{companycode}/{startdate}/{enddate}")
	public StockMongoResponse getAllStocksByDate(
			@Parameter(description = "Company code", example = "Code1") @PathVariable String companycode, 
			@Parameter(description = "Start Date", example = "2014-01-01") @PathVariable Date startdate,
			@Parameter(description = "End Date", example = "2023-01-01") @PathVariable Date enddate) {

		GetStocksQuery stocksQuery = new GetStocksQuery(companycode, startdate, enddate);

		return queryGateway.query(stocksQuery, ResponseTypes.instanceOf(StockMongoResponse.class)).join();

		// StockResponse stocks = stockInformationService.getStocksByDate(companycode,
		// startdate, enddate);
		// return new ResponseEntity<>(stocks, HttpStatus.OK);
	}
	
	

}
