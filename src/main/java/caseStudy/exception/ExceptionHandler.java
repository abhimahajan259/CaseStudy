package caseStudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(AuthorNotExistException.class)
	public ResponseEntity<ErrorDetails> authorNotExistException(AuthorNotExistException ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setCode(ex.getCode());
		errorDetails.setDescription(ex.getDescription());
		errorDetails.setRequest(request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(BookNotExistException.class)
	public ResponseEntity<ErrorDetails> bookNotExistException(BookNotExistException ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setCode(ex.getCode());
		errorDetails.setDescription(ex.getDescription());
		errorDetails.setRequest(request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
