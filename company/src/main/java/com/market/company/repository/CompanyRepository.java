package com.market.company.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.market.company.domain.Company;

/**
 * CompanyRepository
 * 
 * @author User
 *
 */
@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {

	/**
	 * findByCompanyCode
	 * 
	 * @param companyCode companyCode
	 * @return Company
	 */
	public Company findByCompanyCode(String companyCode);

	/**
	 * deleteByCompanyCode
	 * 
	 * @param companyCode companyCode
	 */
	@Modifying
	public void deleteByCompanyCode(String companyCode);

}
