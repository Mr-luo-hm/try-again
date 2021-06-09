package com.again.api.auth.controller;

import com.again.api.auth.config.PulsarProducer;
import com.again.api.auth.model.dto.MessageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Api(tags = "PulsarController", description = "Pulsar功能测试")
@RestController
@RequestMapping("/pulsar")
@RequiredArgsConstructor
public class PulsarController {

	private final PulsarProducer pulsarProducer;

	@ApiOperation("发送消息")
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessage(@RequestBody MessageDto message) throws ExecutionException, InterruptedException {
		for (int i = 100; i > 0; i--) {
			message.setId(i);
			pulsarProducer.syncSend(message);
		}
		pulsarProducer.send(message);
		return "success";
	}

}
