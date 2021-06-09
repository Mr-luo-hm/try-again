package com.again.api.auth.config;

import com.again.api.auth.model.dto.MessageDto;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * pulsar 生产者
 */
@Component
public class PulsarProducer {

	@Autowired
	private PulsarTemplate<MessageDto> template;

	public void send(MessageDto message) {
		try {
			template.send("bootTopic", message);
		}
		catch (PulsarClientException e) {
			e.printStackTrace();
		}
	}

	public void syncSend(MessageDto message) throws ExecutionException, InterruptedException {
		CompletableFuture<MessageId> future = template.sendAsync("syncTopic", message);
		System.out.println(future.get());
	}
}
