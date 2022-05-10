package com.market.company.exception;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBadDeserializer implements Deserializer {

	private final Logger logger = LoggerFactory.getLogger(MyBadDeserializer.class);

	@Override
	public Object deserialize(String topic, byte[] data) {
		logger.error("Deserialization Error");
		return "";
	}

}