package com.sumplem.http;

public enum HttpStatusCode {
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_414_URI_TOO_LONG(400, "URI Too Long"),
    CLIENT_ERROR_404_NOT_FOUND(400, "Not Found"),

    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented");

    public final int CODE;
    public final String MESSAGE;

    HttpStatusCode(int code, String message) {
        this.CODE = code;
        this.MESSAGE = message;
    }
}
