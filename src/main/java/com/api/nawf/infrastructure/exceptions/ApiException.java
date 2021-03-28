package com.api.nawf.infrastructure.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends Exception {
	private static final long serialVersionUID = 5962553423029173333L;

	public final String internalCode;

	public ApiException(String messages) {
		super(messages);
		this.internalCode = "100";
	}

	public ApiException(String messages, String internalCode) {
		super(messages);
		this.internalCode = internalCode;
	}

}
