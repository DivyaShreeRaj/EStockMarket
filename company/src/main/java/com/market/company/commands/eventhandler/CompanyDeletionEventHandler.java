
package com.market.company.commands.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.company.commands.events.CompanyDeletedEvent;
import com.market.company.kafka.producer.KafkaCompanyDeleteProducerService;
import com.market.company.repository.CompanyRepository;
import com.market.company.repository.StockRepository;

@Component
@ProcessingGroup("company")
public class CompanyDeletionEventHandler {

	private Logger log = LoggerFactory.getLogger(CompanyDeletionEventHandler.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private KafkaCompanyDeleteProducerService kafkaCompanyDeleteProducerService;

	@EventHandler
	public String deleteCompanyDetails(CompanyDeletedEvent companyDeletedEvent) throws Exception {

		log.info("Inside CompanyDeletionServiceImpl service with company code: {}",
				companyDeletedEvent.getCompanyCode());

		try {
			stockRepository.deleteByCompanyCode(companyDeletedEvent.getCompanyCode());
			companyRepository.deleteByCompanyCode(companyDeletedEvent.getCompanyCode());

			kafkaCompanyDeleteProducerService.sendMessage(companyDeletedEvent.getCompanyCode());

		} catch (Exception e) {
			log.error("Exception while deleting company details with code:{} DESC:{}",
					companyDeletedEvent.getCompanyCode(), e.getMessage());

			return "Something went wrong while trying to delete a company";
			// throw new CustomRuntimeException("Something went wrong while trying to delete
			// a company", e);
		}

		log.info(" Deletion completed for company with company code: {}", companyDeletedEvent.getCompanyCode());
		return "Company details deleted successfully";
	}

	@ExceptionHandler
	public void handle(Exception exception) throws Exception {
		throw exception;
	}

}
