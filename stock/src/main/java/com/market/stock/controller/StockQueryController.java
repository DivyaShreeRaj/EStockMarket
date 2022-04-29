package com.market.stock.controller;

import java.sql.Date;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.stock.query.GetStocksQuery;
import com.market.stock.response.StockResponse;

@RestController
@RequestMapping(value = "/api/v1.0/market/stock/")
public class StockQueryController {

	@Autowired
	QueryGateway queryGateway;

	@GetMapping(value = "get/{companycode}/{startdate}/{enddate}")
	public StockResponse getAllStocksByDate(@PathVariable String companycode, @PathVariable Date startdate,
			@PathVariable Date enddate) {

		GetStocksQuery stocksQuery = new GetStocksQuery(companycode, startdate, enddate);

		return queryGateway.query(stocksQuery, ResponseTypes.instanceOf(StockResponse.class)).join();

		// StockResponse stocks = stockInformationService.getStocksByDate(companycode,
		// startdate, enddate);
		// return new ResponseEntity<>(stocks, HttpStatus.OK);
	}

}
