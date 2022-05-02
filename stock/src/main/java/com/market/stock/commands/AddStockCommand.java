package com.market.stock.commands;

public class AddStockCommand {

	private String companycode;
	private Double stockPrice;
	
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	public AddStockCommand() {
		super();
	}
	
	public AddStockCommand(String companycode, Double stockPrice) {
		super();
		this.companycode = companycode;
		this.stockPrice = stockPrice;
	}
	
	
}
