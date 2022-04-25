package com.market.stock.domain;


import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "stock_id")
	private Integer Id;

	@Column(name = "company_code")
	private String companyCode;

	@Column(name = "stock_price")
	private Double stockPrice;

	@Column(name = "stock_start_date")
	private Date stockStartDate;

	@Column(name = "stock_end_date")
	private Date stockEndDate;

	@Column(name = "stock_start_time")
	private Time stockStartTime;

	@Column(name = "stock_end_time")
	private Time stockEndTime;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

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

	public Date getStockStartDate() {
		return stockStartDate;
	}

	public void setStockStartDate(Date stockStartDate) {
		this.stockStartDate = stockStartDate;
	}

	public Date getStockEndDate() {
		return stockEndDate;
	}

	public void setStockEndDate(Date stockEndDate) {
		this.stockEndDate = stockEndDate;
	}

	public Time getStockStartTime() {
		return stockStartTime;
	}

	public void setStockStartTime(Time t) {
		this.stockStartTime = t;
	}

	public Time getStockEndTime() {
		return stockEndTime;
	}

	public void setStockEndTime(Time stockEndTime) {
		this.stockEndTime = stockEndTime;
	}

	public Stock(Integer id, String companyCode, Double stockPrice, Date stockStartDate, Date stockEndDate,
			Time stockStartTime, Time stockEndTime) {
		super();
		Id = id;
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockStartDate = stockStartDate;
		this.stockEndDate = stockEndDate;
		this.stockStartTime = stockStartTime;
		this.stockEndTime = stockEndTime;
	}

	public Stock() {
		super();
	}
	

}
