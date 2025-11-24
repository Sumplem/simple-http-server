package com.sumplem.http;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Setter
@Getter
public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String target;
    private String httpVersion;

    public void setMethod(String methodName) {
        for (HttpMethod method : HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = method;
                return;
            }
        }
    }
}
