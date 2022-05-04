package com.market.company.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegistrationRequest {

	@NotBlank(message = "Company code cannot be null and must be unique")
	//@Field("companyCode")
	@JsonProperty("companyCode")
	private String companyCode;

	@NotBlank(message = "Company Name cannot be null")
	//@Field("companyName")
	@JsonProperty("companyName")
	private String companyName;

	@NotBlank(message = "Company CEO cannot be null")
	//@Field("companyCEO")
	@JsonProperty("companyCEO")
	private String companyCEO;

	@NotNull(message = "Company Turnover cannot be null")
	@DecimalMin(value = "100000001.00", message = "Company turnover must be greater than 10Cr")
	//@Field("companyTurnOver")
	@JsonProperty("companyTurnOver")
	private Double companyTurnOver;

	@NotBlank(message = "Company website cannot be null")
	//@Field("companyWebsite")
	@JsonProperty("companyWebsite")
	private String companyWebsite;

	@NotNull(message = "Company stock exchange cannot be null")
	//@Field("stockExchange")
	@JsonProperty("stockExchange")
	private String stockExchange;

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
