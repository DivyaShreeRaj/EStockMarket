
package com.market.company.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.market.company.domain.CompanyDocument;

/**
 * CompanyMongoRepository
 * 
 * @author User
 *
 */
@Repository
public interface CompanyMongoRepository extends MongoRepository<CompanyDocument, String> {

	/**
	 * findByCompanyCode
	 * 
	 * @param companyCode companyCode
	 * @return CompanyDocument
	 */
	public CompanyDocument findByCompanyCode(String companyCode);

	/**
	 * deleteByCompanyCode
	 * 
	 * @param companyCode companyCode
	 */
	public void deleteByCompanyCode(String companyCode);

}
