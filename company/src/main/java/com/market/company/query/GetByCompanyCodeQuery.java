package com.market.company.query;

public class GetByCompanyCodeQuery {
	
	private String companyCode;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public GetByCompanyCodeQuery(String companyCode) {
		super();
		this.companyCode = companyCode;
	}
	
}
