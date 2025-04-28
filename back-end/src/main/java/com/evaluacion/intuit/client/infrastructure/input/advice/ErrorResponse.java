package com.evaluacion.intuit.client.infrastructure.input.advice;

import lombok.ToString;

import java.util.Collections;
import java.util.Map;

@ToString
public class ErrorResponse {

    @ToString.Include
    public final String code;
    @ToString.Include
    public final String text;
    @ToString.Include
    public final Map<String, Object> args;

    public ErrorResponse(String code, String text, Map<String, Object> args) {
        this.code = code;
        this.text = text;
        this.args = args;
    }

    public ErrorResponse(String code, String text) {
        this(code, text, Collections.emptyMap());
    }
}
