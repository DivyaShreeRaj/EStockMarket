package com.market.stock.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMPANY")
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_code")
	private String companyCode;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_ceo")
	private String companyCEO;

	@Column(name = "company_turnover")
	private Double companyTurnOver;

	@Column(name = "company_website")
	private String companyWebsite;

	@Column(name = "stock_exchange")
	private String stockExchange;

	// @OneToMany(mappedBy = "companyObj")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_code")
	private List<Stock> stocks = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
