package com.app.exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.custom_exception.NoContentException;
import com.app.custom_exception.ResourceNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		System.out.println("in method arg invalid " + e);
		List<FieldError> fieldErrors=e.getFieldErrors();
		Map<String,String> map=fieldErrors.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(map);
	}
	
	
	// Handle ResurceNotFound Exception
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> handleNoResourceFoundException(ResourceNotFound e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(e.getMessage());
	}
	
	// Handle NoContentException Exception
		@ExceptionHandler(NoContentException.class)
		public ResponseEntity<?> noContentExceptionException(NoContentException e){
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(e.getMessage());
		}
	
	//Handle RuntimeException
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
	}
}
