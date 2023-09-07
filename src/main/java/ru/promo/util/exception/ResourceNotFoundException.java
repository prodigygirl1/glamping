package ru.promo.util.exception;

import org.springframework.http.HttpStatus;

import static ru.promo.util.exception.BasicError.NOT_FOUND;

public class ResourceNotFoundException extends GlampingRuntimeException {

    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public ResourceNotFoundException(ErrorCode errorCode, String message, Throwable error) {
        super(errorCode, HttpStatus.NOT_FOUND, message, error);
    }

    public static ResourceNotFoundException notFound(String message, Object... args) {
        return new ResourceNotFoundException(NOT_FOUND, String.format(message, args), null);
    }

    public static ResourceNotFoundException notFound() {
        return new ResourceNotFoundException(NOT_FOUND, "Запрашиваемые данные не найдены", null);
    }

    public static ResourceNotFoundException notFound(Throwable error) {
        return new ResourceNotFoundException(NOT_FOUND, error.getMessage(), error);
    }
}