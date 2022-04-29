package com.market.company.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterCompanyCommand {

	@TargetAggregateIdentifier
	public final String companyCode;

	public final String companyName;

	public final String companyCEO;

	public final Double companyTurnOver;

	public final String companyWebsite;

	public final String stockExchange;

	public RegisterCompanyCommand(String companyCode, String companyName, String companyCEO,
			Double companyTurnOver, String companyWebsite, String stockExchange) {
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnOver = companyTurnOver;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
	}
	
	

}
