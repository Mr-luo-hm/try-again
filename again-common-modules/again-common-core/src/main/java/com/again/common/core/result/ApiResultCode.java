package com.again.common.core.result;

import lombok.Getter;

@Getter
public enum ApiResultCode {

	// 成功
	MessageSuccess(200, "message success!"),
	// 500
	ServerError(500, "Server Error !")

	;

	ApiResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private final int code;

	private final String msg;

	public static ApiResultCode getApiResultCodeByMsg(String msgStr) {
		for (ApiResultCode msg : ApiResultCode.values()) {
			if (msgStr.equals(msg.getMsg())) {
				return msg;
			}
		}
		return null;
	}

}
