package com.devsuperior.dscatalog2023.services.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2103714447823804205L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}

}
