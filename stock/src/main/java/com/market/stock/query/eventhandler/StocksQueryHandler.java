package com.market.stock.query.eventhandler;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.stock.domain.Stock;
import com.market.stock.query.GetStocksQuery;
import com.market.stock.repository.StockRepository;
import com.market.stock.response.StockResponse;

@Component
public class StocksQueryHandler {

	@Autowired
	private StockRepository stockRepository;

	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	@QueryHandler
	public StockResponse getStocksByDate(GetStocksQuery getStocksQuery) {

		StockResponse response = new StockResponse();

		List<Stock> stocks = stockRepository.getStocksByDate(getStocksQuery.getCompanyCode(),
				getStocksQuery.getStartdate(), getStocksQuery.getEnddate());

		List<Double> stockPrices = stocks.stream().map(Stock::getStockPrice).collect(Collectors.toList());

		Double minStockPrice = stockPrices.stream().min(Comparator.naturalOrder()).get();
		Double maxStockPrice = stockPrices.stream().max(Comparator.naturalOrder()).get();
		Double avgStockPrice = stockPrices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

		response.setStocks(stocks);
		response.setMinStockPrice(minStockPrice);
		response.setMaxStockPrice(maxStockPrice);
		response.setAvgStockPrice(avgStockPrice);

		return response;
	}

}
