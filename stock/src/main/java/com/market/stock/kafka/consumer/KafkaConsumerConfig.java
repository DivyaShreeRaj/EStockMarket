package com.market.stock.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.market.stock.constants.CommonConstants;
import com.market.stock.domain.Stock;

/**
 * KafkaConsumerConfig
 * 
 * @author User
 *
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	public ConsumerFactory<String, Stock> stockConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, CommonConstants.GROUP_ID);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		JsonDeserializer<Stock> stockJsonDeserializer = new JsonDeserializer<>(Stock.class);
		stockJsonDeserializer.addTrustedPackages("*");
		return new DefaultKafkaConsumerFactory<String, Stock>(config, new StringDeserializer(), stockJsonDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Stock> stockKakfaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Stock> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(stockConsumerFactory());
		return factory;
	}
}
