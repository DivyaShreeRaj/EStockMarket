package com.market.company.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.market.company.domain.Company;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public Company findByCompanyCode(String companyCode);

	@Modifying
	public void deleteByCompanyCode(String companyCode);
	
}
