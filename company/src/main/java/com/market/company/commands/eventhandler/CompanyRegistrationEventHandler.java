package com.market.company.commands.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.company.commands.events.CompanyRegisteredEvent;
import com.market.company.domain.Company;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.kafka.producer.KafkaCompanyRegistrationProducerService;
import com.market.company.repository.CompanyRepository;

@Component
@ProcessingGroup("company")
public class CompanyRegistrationEventHandler {

	private Logger log = LoggerFactory.getLogger(CompanyRegistrationEventHandler.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private KafkaCompanyRegistrationProducerService kafkaCompanyProducerService;

	@EventHandler
	public String registerCompany(CompanyRegisteredEvent companyRegisteredEvent) throws Exception {

		if (companyRegisteredEvent != null && companyRegisteredEvent.getCompanyCode() != null) {

			log.info("Insider Register Company with company code: {}", companyRegisteredEvent.getCompanyCode());

			Company company = new Company();
			BeanUtils.copyProperties(companyRegisteredEvent, company);

			if (checkForExistingCompanyCode(company.getCompanyCode())) {
				log.error(
						"The Company you are trying to register already exists in the system.Please try registration with different company code");
				return "The Company you are trying to register already exists in the system.Please try registration with different company code";
				// throw new DuplicateCompanyRegistrationException();
			} else {

				try {

					/*
					 * company.setCompanyCode(companyRegistrationRequest.getCompanyCode());
					 * company.setCompanyName(companyRegistrationRequest.getCompanyName());
					 * company.setCompanyCEO(companyRegistrationRequest.getCompanyCEO());
					 * company.setCompanyTurnOver(companyRegistrationRequest.getCompanyTurnOver());
					 * company.setCompanyWebsite(companyRegistrationRequest.getCompanyWebsite());
					 * company.setStockExchange(companyRegistrationRequest.getStockExchange());
					 */

					companyRepository.save(company);

					// calling Kafka producer
					kafkaCompanyProducerService.sendMessage(company);

				} catch (Exception e) {

					log.error("Something went wrong while trying to register a company" + e);
					return "Something went wrong while trying to register a company";

					// throw new CustomRuntimeException("Something went wrong while trying to
					// register a company", e);
				}
			}

			log.info(" Registration completed for company with company code: {}",
					companyRegisteredEvent.getCompanyCode());

			// return new ResponseEntity<>("Company Registered successfully",
			// HttpStatus.OK);

			return "Company Registered successfully";

		} else {

			log.error("Details of Company to be registered cannot be null");
			return "Details of Company to be registered cannot be null";
			// throw new CustomRuntimeException("Details of Company to be registered cannot
			// be null");
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

	@ExceptionHandler
	public void handle(Exception exception) throws Exception {
		throw exception;
	}

}
