package com.market.company.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.market.company.domain.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

	public Company findByCompanyCode(String companyCode);

	public void deleteByCompanyCode(String companyCode);
	
}
