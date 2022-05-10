package com.market.company.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.market.company.constants.CommonConstants;

@Service
public class KafkaCompanyDeleteProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaCompanyDeleteProducerService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public KafkaCompanyDeleteProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		logger.info(String.format("Company Delete Message sent -> %s", message));
		this.kafkaTemplate.send(CommonConstants.SECONDARY_TOPIC_NAME, message);
	}

}
