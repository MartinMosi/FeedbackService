package com.mosi.error;

public class InvalidInputException extends Throwable {
	private static final long serialVersionUID = -3953542004882752565L;

	private String message = null;

	public InvalidInputException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "InvalidInputException [message=" + message + "]";
	}
}
