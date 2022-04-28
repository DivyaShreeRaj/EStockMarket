package com.market.stock.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

	@Query("SELECT s from Stock s WHERE s.companyCode =:companyCode "
			+ "AND s.stockStartDate BETWEEN :startdate AND :enddate")
	public List<Stock> getStocksByDate(String companyCode, Date startdate, Date enddate);

}
