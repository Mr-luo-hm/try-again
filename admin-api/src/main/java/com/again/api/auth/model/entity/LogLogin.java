package com.again.api.auth.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogLogin {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Long userId;

	/**
	 * 登录类型 1 登录2登出
	 */
	private Integer logType;

	private LocalDateTime logDate;

}
