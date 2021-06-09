package com.again.api.auth.config;

import com.again.api.auth.model.dto.MessageDto;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfig {

	@Bean
	public ProducerFactory producerFactory() {
		return new ProducerFactory()
				.addProducer("bootTopic", MessageDto.class)
				.addProducer("stringTopic", String.class)
				.addProducer("syncTopic",MessageDto.class);
	}

}
