package com.market.company.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.company.domain.StockMongo;

@Repository
public interface StockMongoRepository extends MongoRepository<StockMongo, Integer> {

	@Query(fields = "{ 'stockPrice' : 1 }", value = "{ 'companyCode' : ?0 }", sort = "{ 'stockStartDate' : -1 , 'stockStartTime' : -1 }")
	// @Query(value = "SELECT s.stockPrice from StockMongo s WHERE s.companyCode
	// =:companyCode ORDER BY s.stockStartDate DESC, s.stockStartTime DESC LIMIT 1",
	// nativeQuery = true)
	List<StockMongo> getLatestStockPrice(String companyCode);

}
