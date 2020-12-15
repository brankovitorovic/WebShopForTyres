package bran.packages.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import bran.packages.cart.exception.EmptyCartException;
import bran.packages.cart.exception.NoSpecificTyreInDatabase;
import bran.packages.user.exception.InvalidUserInfoException;
import bran.packages.user.exception.InvalidUserRoleInfoException;
import bran.packages.user.exception.ProblemWithDatabase;
import bran.packages.user.exception.UserNotFoundException;
import bran.packages.user.exception.UserRoleNotFoundException;

@ControllerAdvice
public class RestControllerAdvice {
	
    @ExceptionHandler(InvalidUserInfoException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidUserInfoException(InvalidUserInfoException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserRoleInfoException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidUserRoleException(InvalidUserRoleInfoException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserRoleNotFoundException(UserRoleNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProblemWithDatabase.class)
    public ResponseEntity<ExceptionResponse> handleProblemWithDatabase(ProblemWithDatabase ex){
    	return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NoSpecificTyreInDatabase.class)
    public ResponseEntity<ExceptionResponse> handleNoSpecificTyreInDatabase(NoSpecificTyreInDatabase ex){
    	return new ResponseEntity<>(new ExceptionResponse(ex),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyCartException(EmptyCartException ex){
    	return new ResponseEntity<>(new ExceptionResponse(ex),HttpStatus.BAD_REQUEST);
    }
    
}
