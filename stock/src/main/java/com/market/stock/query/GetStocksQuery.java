package com.market.stock.query;

import java.sql.Date;

public class GetStocksQuery {

	private String companyCode;
	private Date startdate;
	private Date enddate;

	public GetStocksQuery(String companyCode, Date startdate, Date enddate) {
		super();
		this.companyCode = companyCode;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public GetStocksQuery() {
		super();
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}
