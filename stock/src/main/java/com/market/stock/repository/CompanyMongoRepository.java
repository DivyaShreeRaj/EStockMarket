
package com.market.stock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.CompanyMongo;

@Repository
public interface CompanyMongoRepository extends MongoRepository<CompanyMongo, String> {

	public CompanyMongo findByCompanyCode(String companyCode);
	
}
