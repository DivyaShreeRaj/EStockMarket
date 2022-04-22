package com.market.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.market.company.domain.Company;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.request.CompanyRegistrationRequest;

@Service
public class CompanyRegistrationService {

	private Logger log = LoggerFactory.getLogger(CompanyRegistrationService.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public ResponseEntity<String> registerCompany(CompanyRegistrationRequest companyRegistrationRequest) {

		try {

			if (companyRegistrationRequest != null) {

				log.info("Insider Register Company with company code: {}", companyRegistrationRequest.getCompanyCode());

				Company company = new Company();

				company.setCompanyCode(companyRegistrationRequest.getCompanyCode());
				company.setCompanyName(companyRegistrationRequest.getCompanyName());
				company.setCompanyCEO(companyRegistrationRequest.getCompanyCEO());
				company.setCompanyTurnOver(companyRegistrationRequest.getCompanyTurnOver());
				company.setCompanyWebsite(companyRegistrationRequest.getCompanyWebsite());
				company.setStockExchange(companyRegistrationRequest.getStockExchange());

				mongoTemplate.save(company); // updates if already exists
				// mongoTemplate.insert(companyRegistrationRequest); // throws exception if null
				// obj , already exists

				log.info(" Registration completed for company with company code: {}",
						companyRegistrationRequest.getCompanyCode());

				return new ResponseEntity<>("Company Registered successfully", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Details of Company to be registered cannot be null",
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			throw new CustomRuntimeException("Something went wrong while trying to register a company", e);
		}

	}

}
