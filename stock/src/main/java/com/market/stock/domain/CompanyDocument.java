
package com.market.stock.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CompanyDocument
 * 
 * @author User
 *
 */
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Document("company")
public class CompanyDocument {

	// @MongoId() // @Indexed(unique = true)

	@Id
	private String id;

	private String companyCode;

	private String companyName;

	private String companyCEO;

	private Double companyTurnOver;

	private String companyWebsite;

	private String stockExchange;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public CompanyDocument(String id, String companyCode, String companyName, String companyCEO, Double companyTurnOver,
			String companyWebsite, String stockExchange) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnOver = companyTurnOver;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
	}

}
