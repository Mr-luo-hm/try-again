package com.again.api.auth.config;

import com.again.api.auth.model.dto.MessageDto;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Pulsar消息消费者
 */
@Slf4j
@Component
public class PulsarRealConsumer {

	@PulsarConsumer(topic = "bootTopic", clazz = MessageDto.class)
	public void consume(MessageDto message) {
		log.info("PulsarRealConsumer consume id:{},content:{}", message.getId(), message.getMessage());
	}


	@PulsarConsumer(topic = "syncTopic", clazz = MessageDto.class)
	public void syncConsume(MessageDto message) {
		log.info("PulsarRealConsumer syncConsume id:{},content:{}", message.getId(), message.getMessage());
	}
}
