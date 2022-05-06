package com.market.stock.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.StockMongo;
import com.mongodb.internal.connection.ClusterDescriptionHelper.Predicate;

@Repository
public interface StockMongoRepository extends MongoRepository<StockMongo, Integer> {

	@Query(value = "{ 'companyCode' : ?0 , 'stockStartDate' : { $gt: ?1 , $lt: ?2 } }")
	/*
	 * @Query("SELECT s from StockMongo s WHERE s.companyCode =:companyCode " +
	 * "AND s.stockStartDate BETWEEN :startdate AND :enddate")
	 */
	public List<StockMongo> getStocksByDate(String companyCode, Date startdate, Date enddate);

	/*
	 * @Query(value =
	 * "{ 'companyCode' : ?0, 'stockStartDate' : { $gt: ?1, $lt: ?2 } }") public
	 * List<StockMongo> findStocksByCompanyCodeAndStockStartDateBetween(String
	 * companyCode, Date startdate, Date enddate);
	 * 
	 * @Query(value = "{ 'companyCode' : ?0 }") public List<StockMongo>
	 * findByCompanyCode(String companyCode);
	 */

}
