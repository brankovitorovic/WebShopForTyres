package bran.packages.advice;

import lombok.Getter;

@Getter
class ExceptionResponse {

    private final String message;

    private final Class<?> exceptionClass;

    ExceptionResponse(RuntimeException exception) {
        this.message = exception.getMessage();
        this.exceptionClass = exception.getClass();
    }
}
