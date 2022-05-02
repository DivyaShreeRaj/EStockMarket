package com.market.stock.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.stock.commands.AddStockCommand;

@RestController
@RequestMapping(value = "/api/v1.0/market/stock/")
public class StockCommandController {

	private CommandGateway commandGateway;

	public StockCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PostMapping("add/{companycode}")
	public String addStocks(@RequestParam String companycode, Double stockPrice) {
		
		AddStockCommand addStockCommand = new AddStockCommand(companycode,stockPrice);
		
		try {
			return this.commandGateway.sendAndWait(addStockCommand);
		} catch (Exception exception) {
			throw exception;
		}
		
		//stockManagementService.addStock(companycode, stockPrice);
	}
	
}
