package com.again.cloud.web.controller;

import com.again.cloud.web.entity.SysUser;
import com.again.cloud.web.service.SysUserService;
import com.again.cloud.web.service.api.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SysUserController {
	@Autowired
	SysUserService sysUserService;
	@Autowired
	CacheService cacheService;

	@GetMapping("/load")
	public SysUser loadUserByUsername(String username) {
		cacheService.set("qwertyuioqweq","11111111111111111111111111");
		System.out.println("ssssssssssssssssss");
		SysUser sysUser = sysUserService.getByUsername(username);
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", username);
			throw new RuntimeException("username error!");
		}
		return sysUser;
	}

}
