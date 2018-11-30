package com.ex.forblog.exception;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
public class ExceptionDto {
    private String message;
    private List<FieldError> errors;
}
