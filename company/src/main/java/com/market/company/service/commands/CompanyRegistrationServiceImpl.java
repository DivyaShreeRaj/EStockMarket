package com.market.company.service.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.market.company.domain.Company;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.exception.DuplicateCompanyRegistrationException;
import com.market.company.repository.CompanyRepository;
import com.market.company.request.CompanyRegistrationRequest;

@Service
public class CompanyRegistrationServiceImpl {

	private Logger log = LoggerFactory.getLogger(CompanyRegistrationServiceImpl.class);

	/*
	 * @Autowired MongoTemplate mongoTemplate;
	 */

	@Autowired
	private CompanyRepository companyRepository;

	public ResponseEntity<String> registerCompany(CompanyRegistrationRequest companyRegistrationRequest) {

		if (companyRegistrationRequest != null) {

			log.info("Insider Register Company with company code: {}", companyRegistrationRequest.getCompanyCode());

			if (checkForExistingCompanyCode(companyRegistrationRequest.getCompanyCode())) {
				throw new DuplicateCompanyRegistrationException();
			} else {

				try {
					Company company = new Company();

					company.setCompanyCode(companyRegistrationRequest.getCompanyCode());
					company.setCompanyName(companyRegistrationRequest.getCompanyName());
					company.setCompanyCEO(companyRegistrationRequest.getCompanyCEO());
					company.setCompanyTurnOver(companyRegistrationRequest.getCompanyTurnOver());
					company.setCompanyWebsite(companyRegistrationRequest.getCompanyWebsite());
					company.setStockExchange(companyRegistrationRequest.getStockExchange());

					companyRepository.save(company);
					// mongoTemplate.save(company); // updates if already exists
					// mongoTemplate.insert(companyRegistrationRequest); // throws exception if null
					// obj , already exists
				} catch (Exception e) {
					throw new CustomRuntimeException("Something went wrong while trying to register a company", e);
				}
			}

			log.info(" Registration completed for company with company code: {}",
					companyRegistrationRequest.getCompanyCode());

			return new ResponseEntity<>("Company Registered successfully", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Details of Company to be registered cannot be null", HttpStatus.BAD_REQUEST);
		}

	}

	private boolean checkForExistingCompanyCode(String companyCode) {

		try {

			Company existingCompany = companyRepository.findByCompanyCode(companyCode);
			if (existingCompany != null) {
				return true;
			}

		} catch (Exception e) {
			throw new CustomRuntimeException("Something went wrong while trying to check if company already exists", e);
		}

		return false;
	}

}
