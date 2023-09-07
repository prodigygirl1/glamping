package ru.promo.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Getter
@RestControllerAdvice
@RequiredArgsConstructor
public class WebExceptionHandler extends AbstractWebExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(GlampingRuntimeException.class)
    public ResponseEntity<ResponseError> handleException(GlampingRuntimeException ex) {
        return buildResponse(ex.getErrorCode(), ex.getStatus(), ex.getMessage(), ex.isDetails(), ex);
    }
}
