package com.market.stock.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.stock.response.StockResponse;
import com.market.stock.service.StockInformationService;

@RestController
@RequestMapping(value = "/api/v1.0/market/stock/")
public class StockInformationController {

	@Autowired
	private StockInformationService stockInformationService;

	@GetMapping(value = "get/{companycode}/{startdate}/{enddate}")
	public ResponseEntity<StockResponse> getAllStocksByDate(@PathVariable String companycode,
			@PathVariable Date startdate, @PathVariable Date enddate) {
		StockResponse stocks = stockInformationService.getStocksByDate(companycode, startdate, enddate);
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}
}
