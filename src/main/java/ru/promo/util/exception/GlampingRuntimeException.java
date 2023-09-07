package ru.promo.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlampingRuntimeException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus status;
    private final boolean details;

    public GlampingRuntimeException(ErrorCode errorCode, HttpStatus status, String message) {
        this(errorCode, status, message, null);
    }

    public GlampingRuntimeException(ErrorCode errorCode, HttpStatus status, String message, Throwable error) {
        this(errorCode, status, message, true, error);
    }

    public GlampingRuntimeException(ErrorCode errorCode, HttpStatus status, String message, boolean details, Throwable error) {
        super(message, error);
        this.errorCode = errorCode;
        this.status = status;
        this.details = details;
    }
}
