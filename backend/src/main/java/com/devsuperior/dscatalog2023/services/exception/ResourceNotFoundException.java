package com.devsuperior.dscatalog2023.services.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2103714447823804205L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
