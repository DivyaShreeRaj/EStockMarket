package com.market.stock.commands.events;

import lombok.Builder;
import lombok.Data;

/**
 * StockAdditionEvent
 * 
 * @author User
 *
 */
@Data
@Builder
public class StockAdditionEvent {

	private String companyCode;
	private Double stockPrice;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public StockAdditionEvent() {
		super();
	}

	public StockAdditionEvent(String companyCode, Double stockPrice) {
		super();
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
	}

}
