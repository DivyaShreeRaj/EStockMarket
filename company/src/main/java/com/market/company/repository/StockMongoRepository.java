package com.market.company.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.company.domain.StockDocument;

/**
 * StockMongoRepository
 * 
 * @author User
 *
 */
@Repository
public interface StockMongoRepository extends MongoRepository<StockDocument, Integer> {

	/**
	 * getLatestStockPrice
	 * 
	 * @param companyCode companyCode
	 * @return StockDocument
	 */
	@Query(fields = "{ 'stockPrice' : 1 }", value = "{ 'companyCode' : ?0 }", sort = "{ 'stockStartDate' : -1 , 'stockStartTime' : -1 }")
	List<StockDocument> getLatestStockPrice(String companyCode);

	/**
	 * deleteByCompanyCode
	 * 
	 * @param companyCode companyCode
	 */
	void deleteByCompanyCode(String companyCode);

}
