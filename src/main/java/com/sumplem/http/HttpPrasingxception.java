package com.sumplem.http;

public class HttpPrasingxception extends Exception {
    private HttpStatusCode errorCode;

    public HttpPrasingxception(HttpStatusCode errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }

    public HttpStatusCode getErrorCode() {
        return errorCode;
    }
}
