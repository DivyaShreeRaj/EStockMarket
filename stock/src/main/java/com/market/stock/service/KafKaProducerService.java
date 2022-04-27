package com.market.stock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.market.stock.constants.CommonConstants;

@Service
public class KafKaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafKaProducerService.class);

	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
    public KafKaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;

    }
	
	/*
	 * @Autowired private KafkaTemplate<String, Stock> kafkaTemplate;
	 */
	public void sendMessage(String message) {
		logger.info(String.format("Message sent -> %s", message));
		this.kafkaTemplate.send(CommonConstants.TOPIC_NAME, message);
	}

}
