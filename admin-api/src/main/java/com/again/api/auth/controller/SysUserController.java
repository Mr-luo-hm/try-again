package com.again.api.auth.controller;

import com.again.api.auth.model.entity.SysUser;
import com.again.api.auth.service.SysUserService;
import com.again.api.auth.utils.CacheRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SysUserController {

	private final SysUserService sysUserService;

	private final CacheRedis cacheRedis;

	@GetMapping
	public SysUser loadUserByUsername(String username) {
		SysUser sysUser = sysUserService.getByUsername(username);
		cacheRedis.set("sysUser", sysUser);
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", username);
			throw new RuntimeException("username error!");
		}
		return sysUser;
	}

	public static void main(String[] args) {
		String[] words = { "abc", "abc", "bc" };
		Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
				.stream().sorted((key, value) -> {
					if (key.getValue().equals(value.getValue())) {
						return key.getKey().compareTo(value.getKey());
					}
					else {
						return key.getValue().compareTo(value.getValue());
					}
				}).map(Map.Entry::getKey).limit(1).collect(Collectors.toList()).forEach(System.out::println);
		HashMap<Object, Object> map = new HashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.entrySet().forEach(System.out::println);

	}

}
