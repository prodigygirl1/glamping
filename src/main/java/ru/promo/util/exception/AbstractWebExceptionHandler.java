package ru.promo.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
public abstract class AbstractWebExceptionHandler {

    protected abstract ObjectMapper getObjectMapper();

    public static ResponseEntity<ResponseError> detailResponse(ErrorCode errorCode, HttpStatus status, String msg,
                                                               Throwable ex) {
        return buildResponse(errorCode, status, msg, true, ex);
    }

    public static ResponseEntity<ResponseError> badRequestResponse(ErrorCode errorCode, String msg, Throwable ex) {
        return buildResponse(errorCode, BAD_REQUEST, msg, false, ex);
    }

    public static ResponseEntity<ResponseError> buildResponse(ErrorCode errorCode, HttpStatus status, String msg,
                                                              boolean details, Throwable ex) {
        return buildResponse(errorCode.name(), status, msg, details, ex, LocalDateTime.now());
    }

    public static ResponseEntity<ResponseError> buildResponse(String errorCode, HttpStatus status, String msg,
                                                              boolean details, Throwable ex, LocalDateTime timestamp) {
        if (details) {
            log.error(msg, ex); // with stack-trace
        } else {
            var message = StringUtils.equals(msg, ex.getMessage()) ? msg : msg + ": " + ex.getMessage();
            if (status == NOT_FOUND) {
                log.warn(message);
            } else {
                log.error(message);
            }
        }

        return ResponseEntity.status(status.value())
                .body(ResponseError.builder()
                        .code(errorCode)
                        .message(msg)
                        .timestamp(timestamp)
                        .status(status.value())
                        .build());
    }
}
