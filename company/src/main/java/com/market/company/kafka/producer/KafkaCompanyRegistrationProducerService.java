package com.market.company.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.market.company.constants.CommonConstants;
import com.market.company.domain.Company;

@Service
public class KafkaCompanyRegistrationProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaCompanyRegistrationProducerService.class);

	@Autowired
	private KafkaTemplate<String, Company> kafkaTemplate;

	@Autowired
	public KafkaCompanyRegistrationProducerService(KafkaTemplate<String, Company> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(Company company) {
		logger.info(String.format("Company Registration Message sent -> %s", company));
		this.kafkaTemplate.send(CommonConstants.TOPIC_NAME, company);
	}


}
