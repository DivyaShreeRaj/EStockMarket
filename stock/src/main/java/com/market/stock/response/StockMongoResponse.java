package com.market.stock.response;

import java.util.List;

import com.market.stock.domain.StockMongo;

public class StockMongoResponse {

	public List<StockMongo> stocks;

	public Double minStockPrice;

	public Double maxStockPrice;

	public Double avgStockPrice;

	public StockMongoResponse() {
		super();
	}

	public StockMongoResponse(List<StockMongo> stocks, Double minStockPrice, Double maxStockPrice, Double avgStockPrice) {
		super();
		this.stocks = stocks;
		this.minStockPrice = minStockPrice;
		this.maxStockPrice = maxStockPrice;
		this.avgStockPrice = avgStockPrice;
	}

	public List<StockMongo> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockMongo> stocks) {
		this.stocks = stocks;
	}

	public Double getMinStockPrice() {
		return minStockPrice;
	}

	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}

	public Double getMaxStockPrice() {
		return maxStockPrice;
	}

	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}

	public Double getAvgStockPrice() {
		return avgStockPrice;
	}

	public void setAvgStockPrice(Double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}

}
