package com.market.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public Company findByCompanyCode(String companyCode);

}
