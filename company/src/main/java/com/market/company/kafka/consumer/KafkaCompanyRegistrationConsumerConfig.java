package com.market.company.kafka.consumer;

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

import com.market.company.constants.CommonConstants;
import com.market.company.domain.Company;

@Configuration

@EnableKafka
public class KafkaCompanyRegistrationConsumerConfig {

	public ConsumerFactory<String, Company> companyConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, CommonConstants.GROUP_ID);

		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		JsonDeserializer<Company> companyJsonDeserializer = new JsonDeserializer<>(Company.class);
		companyJsonDeserializer.addTrustedPackages("*");

		return new DefaultKafkaConsumerFactory<String, Company>(config, new StringDeserializer(),
				companyJsonDeserializer);

	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Company> companyRegistrationKakfaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Company> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(companyConsumerFactory());
		return factory;
	}

}
