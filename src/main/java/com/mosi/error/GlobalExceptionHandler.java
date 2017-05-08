package com.mosi.error;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mosi.feedback.rest.FeedbackController;

/**
 * Main global exception handler. This is the place where we can handle all
 * global exceptions caused by this service.
 * 
 * @author Martin
 *
 */
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	/**
	 * Global exception handler for all exceptions
	 * 
	 * @param request
	 *            HTTP request
	 * @param exception
	 *            exception object
	 * @return error response
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse unknownException(HttpServletRequest request, Exception exception) {

		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
	}

	/**
	 * Catch exception if its requested non existing end point
	 * 
	 * @param request
	 *            HTTP request
	 * @param exception
	 *            exception object
	 * @return error response
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse noHandlerFoundException(HttpServletRequest request, Exception exception) {
		logger.error("No handler found for request: " + request.getRequestURL(), exception);

		return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}
}
