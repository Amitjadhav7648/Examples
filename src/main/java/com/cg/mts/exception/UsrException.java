package com.cg.mts.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsrException {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ Exception.class })
	public ErrorMapper handleConflict(Exception ex, HttpServletRequest req) {
		String msg = ex.getMessage();
		String uri = req.getRequestURL().toString();
		return new ErrorMapper(uri, msg, new Date());
	}
}
