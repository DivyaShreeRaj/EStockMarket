package com.market.stock.commands.eventhandler;

import java.sql.Time;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.stock.commands.events.StockAdditionEvent;
import com.market.stock.domain.Stock;
import com.market.stock.kafka.producer.KafkaStockProducerService;
import com.market.stock.repository.StockRepository;

/**
 * StockAdditionCommandHandler
 * 
 * @author User
 *
 */
@Component
@ProcessingGroup("stock")
public class StockAdditionCommandHandler {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private KafkaStockProducerService kafkaStockProducerService;

	private final Logger logger = LoggerFactory.getLogger(StockAdditionCommandHandler.class);

	@EventHandler
	public void addStock(StockAdditionEvent stockAdditionEvent) {

		try {
			GregorianCalendar gcalendar = new GregorianCalendar();
			@SuppressWarnings("deprecation")
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

			// Calling kakfa producer service
			kafkaStockProducerService.sendMessage(stock);

		} catch (Exception e) {
			/*
			 * throw new CustomRuntimeException(
			 * "Something went wrong while trying to add Stocks, Please ensure the company is registered before adding stocks."
			 * , e);
			 */
			logger.error(
					"Something went wrong while trying to add Stocks, Please ensure the company is registered before adding stocks.");
		}

	}

}
