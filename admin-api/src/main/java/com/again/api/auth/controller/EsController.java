package com.again.api.auth.controller;

import com.again.api.auth.service.EsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EsController {

	private final EsService esService;

	@GetMapping("/index")
	public boolean addIndex() {
		try {
			return esService.createIndex("scrm-bus-admin-es", "doc_");
		}
		catch (IOException e) {
			log.error("addIndex error:{}", e.getMessage(), e);
			return false;
		}
	}

	@GetMapping("/test")
	public String hello() {
		return "hello";
	}

}
