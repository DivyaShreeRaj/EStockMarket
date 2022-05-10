package com.market.company.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.market.company.constants.CommonConstants;
import com.market.company.domain.Company;
import com.market.company.domain.CompanyDocument;
import com.market.company.repository.CompanyMongoRepository;
import com.market.company.repository.StockMongoRepository;

@Service
public class KafKaCompanyConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafKaCompanyConsumerService.class);

	@Autowired
	private CompanyMongoRepository companyMongoRepository;

	@Autowired
	private StockMongoRepository stockMongoRepository;

	@KafkaListener(topics = CommonConstants.TOPIC_NAME, groupId = CommonConstants.GROUP_ID, containerFactory = "companyRegistrationKakfaListenerFactory")
	public void consume(Company company) {
		logger.info(String.format("Register Company Message recieved -> %s", company));

		try {

			logger.info("Syncing Mongo DB for Company Registration event: {}", company.getCompanyCode());
			CompanyDocument companyDocument = new CompanyDocument();
			BeanUtils.copyProperties(company, companyDocument);
			if (null != companyDocument) {
				companyMongoRepository.save(companyDocument);
				logger.info("Sync Successful on Mongo DB for Company Registration event: {}",
						companyDocument.getCompanyCode());
			}

		} catch (Exception e) {
			logger.error(" Something went wrong while Syncing Mongo DB for Company Registration event: {}",
					company.getCompanyCode());
		}
	}

	@KafkaListener(topics = CommonConstants.SECONDARY_TOPIC_NAME, groupId = CommonConstants.GROUP_ID, containerFactory = "companyDeleteKakfaListenerFactory")
	public void consume(String companyCode) {
		logger.info(String.format("Delete Company Message recieved -> %s", companyCode));

		try {

			logger.info("Syncing Mongo DB for Company Deletion event: {}", companyCode);

			stockMongoRepository.deleteByCompanyCode(companyCode);
			companyMongoRepository.deleteByCompanyCode(companyCode);

			logger.info("Sync Successful on Mongo DB for Company Deletion event: {}", companyCode);

		} catch (Exception e) {
			logger.error(" Something went wrong while Syncing Mongo DB for Company Deletion event: {}", companyCode);
		}
	}

}
