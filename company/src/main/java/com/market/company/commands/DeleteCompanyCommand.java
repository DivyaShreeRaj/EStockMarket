package com.market.company.commands;

public class DeleteCompanyCommand {

	public final String companyCode;

	public DeleteCompanyCommand(String id, String companyCode) {
		this.companyCode = companyCode;
	}
	
}
