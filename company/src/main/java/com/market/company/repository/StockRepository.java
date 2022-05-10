package com.market.company.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.market.company.domain.Stock;

/**
 * StockRepository
 * 
 * @author User
 *
 */
@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Integer> {

	/**
	 * getLatestStockPrice
	 * 
	 * @param companyCode companyCode
	 * @return Double
	 */
	@Query(value = "SELECT s.stock_price from Stock s WHERE s.company_code =:companyCode ORDER BY s.stock_start_date DESC, s.stock_start_time DESC LIMIT 1", nativeQuery = true)
	public Double getLatestStockPrice(@Param("companyCode") String companyCode);

	/**
	 * getLatestStockPrice
	 * 
	 * @param companyCode companyCode
	 */
	@Modifying
	public void deleteByCompanyCode(String companyCode);

}
