package org.jobboard.adapter.out.persistence.jpa.vacancy.exception;


import org.jobboard.adapter.out.persistence.jpa.common.RepositoryException;

public class VacancyRepositoryException extends RepositoryException {
	public VacancyRepositoryException() {
	}

	public VacancyRepositoryException(String message) {
		super(message);
	}
}
