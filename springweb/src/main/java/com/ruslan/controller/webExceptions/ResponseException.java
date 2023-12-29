package com.ruslan.controller.webExceptions;

public class ResponseException {

    private final String errorMessage;
    private final String errorCode;

    public ResponseException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
