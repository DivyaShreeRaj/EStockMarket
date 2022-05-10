
package com.market.company.domain;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
@Document("stock")
public class StockDocument {

	@Id
	private String id;
	private Integer stockId;
	private String companyCode;
	private Double stockPrice;
	private Date stockStartDate;
	private Date stockEndDate;
	private String stockStartTime;
	private String stockEndTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
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

	public String getStockStartTime() {
		return stockStartTime;
	}

	public void setStockStartTime(String t) {
		this.stockStartTime = t;
	}

	public String getStockEndTime() {
		return stockEndTime;
	}

	public void setStockEndTime(String stockEndTime) {
		this.stockEndTime = stockEndTime;
	}

	public StockDocument(String id, Integer stockId, String companyCode, Double stockPrice, Date stockStartDate,
			Date stockEndDate, String stockStartTime, String stockEndTime) {
		super();
		this.id = id;
		this.stockId = stockId;
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockStartDate = stockStartDate;
		this.stockEndDate = stockEndDate;
		this.stockStartTime = stockStartTime;
		this.stockEndTime = stockEndTime;
	}

	public StockDocument(Integer id, String companyCode, Double stockPrice, Date stockStartDate, Date stockEndDate,
			String stockStartTime, String stockEndTime) {
		super();
		this.stockId = id;
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockStartDate = stockStartDate;
		this.stockEndDate = stockEndDate;
		this.stockStartTime = stockStartTime;
		this.stockEndTime = stockEndTime;
	}

	public StockDocument() {
		super();
	}

}
