package com.market.stock.aggregator;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.market.stock.commands.AddStockCommand;
import com.market.stock.event.StockAdditionEvent;

@Aggregate
public class StockAggregate {

	@AggregateIdentifier
	private String id;

	private String companyCode;

	private Double stockPrice;

	private Date stockStartDate;

	private Date stockEndDate;

	private Time stockStartTime;

	private Time stockEndTime;

	@CommandHandler
	public StockAggregate(AddStockCommand addStockCommand) {

		// Here we can handle validation logic on registerCompanyCommand object
		StockAdditionEvent stockAdditionEvent = new StockAdditionEvent();

		stockAdditionEvent.setCompanyCode(addStockCommand.getCompanycode());
		stockAdditionEvent.setStockPrice(addStockCommand.getStockPrice());
		//BeanUtils.copyProperties(addStockCommand, stockAdditionEvent);

		// it invokes event source handler method (companyEvent) with company created
		// event
		AggregateLifecycle.apply(stockAdditionEvent);
	}

	/**
	 * Method to store the CompanyRegisteredEvent in the eventstore
	 *
	 * @param companyRegisteredEvent
	 */
	@EventSourcingHandler
	public void stockEvent(StockAdditionEvent stockAdditionEvent) {
		this.id = UUID.randomUUID().toString();
		this.companyCode = stockAdditionEvent.getCompanyCode();
		this.stockPrice = stockAdditionEvent.getStockPrice();
	
	}

}
