package com.evaluacion.intuit.client.application.exceptions;

public class OperationNotValidException extends RuntimeException {
    public final String messageId;

    public OperationNotValidException(String messageId, String message) {
        super(message);
        this.messageId = messageId;
    }
}
