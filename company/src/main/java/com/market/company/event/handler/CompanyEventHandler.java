package com.market.company.event.handler;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.market.company.domain.Company;
import com.market.company.events.CompanyRegisteredEvent;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.exception.DuplicateCompanyRegistrationException;
import com.market.company.repository.CompanyRepository;
import com.market.company.request.CompanyRegistrationRequest;

@Component
public class CompanyEventHandler {

	private Logger log = LoggerFactory.getLogger(CompanyEventHandler.class);

	@Autowired
	private CompanyRepository companyRepository;

	@EventHandler
	public ResponseEntity<String> registerCompany(CompanyRegisteredEvent companyRegisteredEvent) {

		
		if (companyRegisteredEvent != null) {

			log.info("Insider Register Company with company code: {}", companyRegisteredEvent.getCompanyCode());

			if (checkForExistingCompanyCode(companyRegisteredEvent.getCompanyCode())) {
				throw new DuplicateCompanyRegistrationException();
			} else {

				try {
					Company company = new Company();
					BeanUtils.copyProperties(companyRegisteredEvent, company);

					/*
					 * company.setCompanyCode(companyRegistrationRequest.getCompanyCode());
					 * company.setCompanyName(companyRegistrationRequest.getCompanyName());
					 * company.setCompanyCEO(companyRegistrationRequest.getCompanyCEO());
					 * company.setCompanyTurnOver(companyRegistrationRequest.getCompanyTurnOver());
					 * company.setCompanyWebsite(companyRegistrationRequest.getCompanyWebsite());
					 * company.setStockExchange(companyRegistrationRequest.getStockExchange());
					 */

					companyRepository.save(company);
					// mongoTemplate.save(company); // updates if already exists
					// mongoTemplate.insert(companyRegistrationRequest); // throws exception if null
					// obj , already exists
				} catch (Exception e) {
					throw new CustomRuntimeException("Something went wrong while trying to register a company", e);
				}
			}

			log.info(" Registration completed for company with company code: {}",
					companyRegisteredEvent.getCompanyCode());

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