package com.market.company.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Getter
@Setter
public class CompanyRegisteredEvent{ // extends BaseEvent<String> {

	public /* final */ String companyCode;

	public /* final */ String companyName;

	public /* final */ String companyCEO;

	public /* final */ Double companyTurnOver;

	public /* final */ String companyWebsite;

	public /* final */ String stockExchange;

	public CompanyRegisteredEvent() {
		
	}

	public CompanyRegisteredEvent(String id, String companyCode, String companyName, String companyCEO,
			Double companyTurnOver, String companyWebsite, String stockExchange) {
		//super(id);
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnOver = companyTurnOver;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
	}

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
	
	

}
