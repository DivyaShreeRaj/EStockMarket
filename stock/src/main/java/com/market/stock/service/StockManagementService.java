package com.market.stock.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.stock.domain.Stock;
import com.market.stock.repository.StockRepository;

@Service
public class StockManagementService {

	@Autowired
	private StockRepository stockRepository;

	public void addStock(String companyCode, Double stockPrice) {

		/*
		 * LocalDateTime myDateObj = LocalDateTime.now(); DateTimeFormatter myFormatObj
		 * = DateTimeFormatter.ofPattern("HH:mm:ss");
		 * 
		 * String formattedDate = myDateObj.format(myFormatObj);
		 * 
		 */
		GregorianCalendar gcalendar = new GregorianCalendar();
		Time t = new Time(gcalendar.get(Calendar.HOUR), gcalendar.get(Calendar.MINUTE), gcalendar.get(Calendar.SECOND));

		Stock stock = new Stock();
		stock.setCompanyCode(companyCode);
		stock.setStockPrice(stockPrice);
		stock.setStockStartDate(Time.from(Instant.now()));
		stock.setStockEndDate(new Date());
		stock.setStockStartTime(t);
		stock.setStockEndTime(t);

		stockRepository.save(stock);

	}

}
