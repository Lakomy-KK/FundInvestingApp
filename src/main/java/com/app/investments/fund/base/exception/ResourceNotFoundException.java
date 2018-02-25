package com.app.investments.fund.base.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Krzysztof Lakomy
 */
@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	private String message;

	public ResourceNotFoundException(String message){
		this.message = message;
	}

}
