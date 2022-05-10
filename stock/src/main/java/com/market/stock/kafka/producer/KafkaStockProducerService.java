package com.market.stock.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.market.stock.constants.CommonConstants;
import com.market.stock.domain.Stock;

/**
 * KafkaStockProducerService
 * 
 * @author User
 *
 */
@Service
public class KafkaStockProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaStockProducerService.class);

	@Autowired
	private KafkaTemplate<String, Stock> kafkaTemplate;

	@Autowired
	public KafkaStockProducerService(KafkaTemplate<String, Stock> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * sendMessage
	 * 
	 * @param stock stock
	 */
	public void sendMessage(Stock stock) {
		logger.info(String.format("Message sent -> %s", stock));
		this.kafkaTemplate.send(CommonConstants.TOPIC_NAME, stock);
	}

}
