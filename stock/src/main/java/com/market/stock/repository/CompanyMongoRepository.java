
package com.market.stock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.CompanyDocument;

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

}
