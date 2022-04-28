package com.market.company.response;

public class CompanyInfoResponse {

	private String companyCode;

	private String companyName;

	private String companyCEO;

	private Double companyTurnOver;

	private String companyWebsite;

	private String stockExchange;

	private Double latestStockPrice;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public Double getCompanyTurnOver() {
		return companyTurnOver;
	}

	public void setCompanyTurnOver(Double companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public Double getLatestStockPrice() {
		return latestStockPrice;
	}

	public void setLatestStockPrice(Double latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}

	public CompanyInfoResponse(String companyCode, String companyName, String companyCEO, Double companyTurnOver,
			String companyWebsite, String stockExchange, Double latestStockPrice) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnOver = companyTurnOver;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.latestStockPrice = latestStockPrice;
	}

	public CompanyInfoResponse() {
		super();
	}

}
