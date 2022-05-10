package com.market.stock.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.market.stock.constants.CommonConstants;
import com.market.stock.domain.Stock;
import com.market.stock.domain.StockDocument;
import com.market.stock.repository.StockMongoRepository;

/**
 * KafKaStockConsumerService
 * 
 * @author User
 *
 */
@Service
public class KafKaStockConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafKaStockConsumerService.class);

	@Autowired
	private StockMongoRepository stockMongoRepository;

	/**
	 * consume
	 * 
	 * @param stock stock
	 */
	@KafkaListener(topics = CommonConstants.TOPIC_NAME, groupId = CommonConstants.GROUP_ID, containerFactory = "stockKakfaListenerFactory")
	public void consume(Stock stock) {
		logger.info(String.format("Message recieved -> %s", stock));

		try {

			logger.info("Syncing Mongo DB for Stock Addition event: {}", stock.getCompanyCode());

			StockDocument stockDocument = new StockDocument();
			stockDocument.setStockId(stock.getId());
			stockDocument.setCompanyCode(stock.getCompanyCode());
			stockDocument.setStockStartDate(stock.getStockStartDate());
			stockDocument.setStockEndDate(stock.getStockEndDate());
			stockDocument.setStockStartTime(stock.getStockStartTime().toString());
			stockDocument.setStockEndTime(stock.getStockEndTime().toString());
			stockDocument.setStockPrice(stock.getStockPrice());

			if (null != stockDocument) {
				stockMongoRepository.save(stockDocument);
				logger.info("Sync Successful on Mongo DB for Stock Addition event: {}", stockDocument.getCompanyCode());
			}

		} catch (Exception e) {
			logger.error(" Something went wrong while Syncing Mongo DB for Stock Addition event: {}",
					stock.getCompanyCode());
		}

	}

}
