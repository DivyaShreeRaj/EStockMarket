package com.market.stock.commands.eventhandler;

import java.sql.Time;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.stock.commands.events.StockAdditionEvent;
import com.market.stock.domain.Stock;
import com.market.stock.exception.CustomRuntimeException;
import com.market.stock.repository.StockRepository;

@Component
@ProcessingGroup("stock")
public class StockAdditionCommandHandler {

	@Autowired
	private StockRepository stockRepository;
	
	@EventHandler
	public void addStock(StockAdditionEvent stockAdditionEvent) {

		try {
			GregorianCalendar gcalendar = new GregorianCalendar();
			Time t = new Time(gcalendar.get(Calendar.HOUR), gcalendar.get(Calendar.MINUTE),
					gcalendar.get(Calendar.SECOND));

			Stock stock = new Stock();
			stock.setCompanyCode(stockAdditionEvent.getCompanyCode());
			stock.setStockPrice(stockAdditionEvent.getStockPrice());
			stock.setStockStartDate(Time.from(Instant.now()));
			stock.setStockEndDate(new Date());
			stock.setStockStartTime(t);
			stock.setStockEndTime(t);

			stockRepository.save(stock);
		} catch (Exception e) {
			throw new CustomRuntimeException(
					"Something went wrong while trying to add Stocks, Please ensure the company is registered before adding stocks.",
					e);
		}

	}
	
}
