package com.market.company.aggregator;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.market.company.commands.RegisterCompanyCommand;
import com.market.company.events.CompanyRegisteredEvent;

@Aggregate
public class CompanyAggregate {

	@AggregateIdentifier
	private String companyCode;

	private String companyName;

	private String companyCEO;

	private Double companyTurnOver;

	private String companyWebsite;

	private String stockExchange;

	public CompanyAggregate() {

	}

	@CommandHandler
	public CompanyAggregate(RegisterCompanyCommand registerCompanyCommand) {

		// Here we can handle validation logic on registerCompanyCommand object
		CompanyRegisteredEvent companyRegisteredEvent = new CompanyRegisteredEvent();

		BeanUtils.copyProperties(registerCompanyCommand, companyRegisteredEvent);
		// it invokes event source handler method (companyEvent) with company created
		// event

		AggregateLifecycle.apply(companyRegisteredEvent);
	}

	/**
	 * Method to store the CompanyRegisteredEvent in the eventstore
	 *
	 * @param companyRegisteredEvent
	 */
	@EventSourcingHandler
	public void companyEvent(CompanyRegisteredEvent companyRegisteredEvent) {
		this.companyCode = companyRegisteredEvent.getCompanyCode();
		this.companyName = companyRegisteredEvent.getCompanyName();
		this.companyCEO = companyRegisteredEvent.getCompanyCEO();
		this.companyTurnOver = companyRegisteredEvent.getCompanyTurnOver();
		this.companyWebsite = companyRegisteredEvent.getCompanyWebsite();
		this.stockExchange = companyRegisteredEvent.getStockExchange();
	}

}
