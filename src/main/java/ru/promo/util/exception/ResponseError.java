package ru.promo.util.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseError {
    @JsonProperty(required = true)
    private LocalDateTime timestamp;
    @JsonProperty(required = true)
    private Integer status;
    @JsonProperty(required = true)
    private String message;
    @JsonProperty(required = true)
    private String code;
}
