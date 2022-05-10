package com.market.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.Company;

/**
 * CompanyRepository
 * 
 * @author User
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	/**
	 * findByCompanyCode
	 * 
	 * @param companyCode companyCode
	 * @return Company
	 */
	public Company findByCompanyCode(String companyCode);

}
