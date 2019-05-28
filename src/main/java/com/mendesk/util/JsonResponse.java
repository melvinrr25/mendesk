package com.mendesk.util;

import org.springframework.stereotype.Component;

public class JsonResponse {
	private String message;
	private Integer status;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public JsonResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	
}
