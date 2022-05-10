package com.market.stock.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.stock.commands.AddStockCommand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * StockCommandController
 * 
 * @author User
 *
 */
@RestController
@RequestMapping(value = "/api/v1.0/market/stock/")
@Tag(name = "Stock Commands", description = "This is a controller for command operations on Stock Resource")
public class StockCommandController {

	private CommandGateway commandGateway;

	public StockCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	/**
	 * addStocks
	 * 
	 * @param companycode companycode
	 * @param stockPrice  stockPrice
	 * @return String
	 */
	@Operation(summary = "Add Stocks to provided Company")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@PostMapping("add/{companycode}")
	public String addStocks(
			@RequestParam @Parameter(description = "Company code", example = "Code1") String companycode,
			@Parameter(description = "Stock Price", example = "100.00") Double stockPrice) {

		AddStockCommand addStockCommand = new AddStockCommand(companycode, stockPrice);

		try {
			return this.commandGateway.sendAndWait(addStockCommand);
		} catch (Exception exception) {
			throw exception;
		}

		// stockManagementService.addStock(companycode, stockPrice);
	}

}
