package com.mosi.error;

import org.springframework.http.HttpStatus;

/**
 * Error Response object. Standard DTO object independent to current used error
 * handling technology (Spring boot)
 * 
 * @author Martin
 *
 */
public class ErrorResponse {
	private HttpStatus status;
	private String message;

	public ErrorResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", message=" + message + "]";
	}
}
