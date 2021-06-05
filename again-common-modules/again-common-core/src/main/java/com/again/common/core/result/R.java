package com.again.common.core.result;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 返回状态码
	 */
	private int code;

	/**
	 * 返回信息
	 */

	private String message;

	/**
	 * 数据
	 */
	private T data;

	public static <T> R<T> ok() {
		return ok(null);
	}

	public static <T> R<T> ok(T data) {
		return new R<T>().setCode(SystemResultCode.SUCCESS.getCode()).setData(data)
				.setMessage(SystemResultCode.SUCCESS.getMessage());
	}

	public static <T> R<T> ok(T data, String message) {
		return new R<T>().setCode(SystemResultCode.SUCCESS.getCode()).setData(data).setMessage(message);
	}

	public static <T> R<T> failed(int code, String message) {
		return new R<T>().setCode(code).setMessage(message);
	}

	public static <T> R<T> failed(ResultCode failMsg) {
		return new R<T>().setCode(failMsg.getCode()).setMessage(failMsg.getMessage());
	}

	public static <T> R<T> failed(ResultCode failMsg, String message) {
		return new R<T>().setCode(failMsg.getCode()).setMessage(message);
	}

}
