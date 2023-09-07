package ru.promo.util.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static ru.promo.util.exception.BasicError.REQUEST_INVALID;
import static ru.promo.util.exception.BasicError.REQUEST_INVALID_PARAMETER;

public class BadRequestException extends GlampingRuntimeException {
    public BadRequestException(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public BadRequestException(ErrorCode errorCode, String message, Throwable error) {
        super(errorCode, HttpStatus.BAD_REQUEST, message, error);
    }

    public static BadRequestException invalidParam(String message, Object... parameters) {
        var msg = String.format("Задан неверный параметр '%s': %s", message, Arrays.asList(parameters));
        return new BadRequestException(REQUEST_INVALID_PARAMETER, msg, null);
    }

    public static BadRequestException invalidMessage(String message, Object... args) {
        return new BadRequestException(REQUEST_INVALID, String.format(message, args), null);
    }
}
