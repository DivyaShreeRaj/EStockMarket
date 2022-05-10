package com.market.stock.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.StockDocument;

/**
 * StockMongoRepository
 * 
 * @author User
 *
 */
@Repository
public interface StockMongoRepository extends MongoRepository<StockDocument, Integer> {

	/**
	 * getStocksByDate
	 * 
	 * @param companyCode companyCode
	 * @param startdate   startdate
	 * @param enddate     enddate
	 * @return List<StockDocument>
	 */
	@Query(value = "{ 'companyCode' : ?0 , 'stockStartDate' : { $gt: ?1 , $lt: ?2 } }")
	public List<StockDocument> getStocksByDate(String companyCode, Date startdate, Date enddate);

}
