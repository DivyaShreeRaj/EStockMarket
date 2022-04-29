package com.market.company.events;

public class CompanyDeletedEvent extends BaseEvent<String> {

	public final String companyCode;

	public CompanyDeletedEvent(String id, String companyCode) {
		super(id);
		this.companyCode = companyCode;
	}
	
}
