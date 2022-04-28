package com.market.stock.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.stock.domain.Company;
import com.market.stock.domain.Stock;
import com.market.stock.exception.CustomRuntimeException;
import com.market.stock.repository.CompanyRepository;
import com.market.stock.repository.StockRepository;

@Service
public class StockManagementService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private CompanyRepository companyRepository;

	public void addStock(String companyCode, Double stockPrice) {

		try {
			GregorianCalendar gcalendar = new GregorianCalendar();
			Time t = new Time(gcalendar.get(Calendar.HOUR), gcalendar.get(Calendar.MINUTE),
					gcalendar.get(Calendar.SECOND));

			Stock stock = new Stock();
			stock.setCompanyCode(companyCode);

			stock.setStockPrice(stockPrice);
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
