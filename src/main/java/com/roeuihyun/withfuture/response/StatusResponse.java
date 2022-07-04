package com.roeuihyun.withfuture.response;

import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class StatusResponse {

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Validation> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Validation {
    
        private final String field;
        private final String message;

        public static Validation of(final FieldError fieldError) {
            return Validation.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}