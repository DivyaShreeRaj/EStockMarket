package com.market.company.aggregator;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.market.company.commands.DeleteCompanyCommand;
import com.market.company.commands.RegisterCompanyCommand;
import com.market.company.commands.events.CompanyDeletedEvent;
import com.market.company.commands.events.CompanyRegisteredEvent;

@Aggregate
public class CompanyAggregate {

	@AggregateIdentifier
	private String id;

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
		this.id = UUID.randomUUID().toString();
		this.companyCode = companyRegisteredEvent.getCompanyCode();
		this.companyName = companyRegisteredEvent.getCompanyName();
		this.companyCEO = companyRegisteredEvent.getCompanyCEO();
		this.companyTurnOver = companyRegisteredEvent.getCompanyTurnOver();
		this.companyWebsite = companyRegisteredEvent.getCompanyWebsite();
		this.stockExchange = companyRegisteredEvent.getStockExchange();
	}

	@CommandHandler
	public CompanyAggregate(DeleteCompanyCommand deleteCompanyCommand) {

		CompanyDeletedEvent companyDeletedEvent = new CompanyDeletedEvent();

		BeanUtils.copyProperties(deleteCompanyCommand, companyDeletedEvent);

		AggregateLifecycle.apply(companyDeletedEvent);

		// AggregateLifecycle.markDeleted();

	}

	/**
	 * Method to store the CompanyDeletedEvent in the eventstore
	 *
	 * @param companyDeletedEvent
	 */
	@EventSourcingHandler
	public void companyEvent(CompanyDeletedEvent companyDeletedEvent) {
		this.id = UUID.randomUUID().toString();
		this.companyCode = companyDeletedEvent.getCompanyCode();
	}

}
