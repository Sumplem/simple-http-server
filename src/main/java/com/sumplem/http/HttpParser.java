package com.sumplem.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpParser {
    private final int SP = 32;
    private final int CR = 13;
    private final int LF = 10;

    public HttpRequest parseRequest(InputStream inputStream) throws HttpPrasingxception {
        HttpRequest request = new HttpRequest();

        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

        try {
            parseRequestLine(input, request);
            parseStatusLine(input, request);
        } catch (IOException e) {
            throw new HttpPrasingxception(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }

        return request;
    }

    private void parseRequestLine(BufferedReader input, HttpRequest request) throws IOException, HttpPrasingxception {
        StringBuilder buffer = new StringBuilder();
        boolean methodParsed = false;
        boolean targetParsed = false;
        boolean versionParsed = false;
        int _byte;
        while ((_byte = input.read()) >= 0) {
            if (_byte == CR) {
                if (input.read() != LF) {
                    throw new HttpPrasingxception(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                if (!methodParsed || !targetParsed || !versionParsed) {
                    throw new HttpPrasingxception(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                return;
            }

            if (!versionParsed) {
                throw new HttpPrasingxception(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
            }

            if (_byte != SP) {
                buffer.append((char) _byte);
                if (!methodParsed && buffer.length() > HttpMethod.MAX_LENGTH) {
                    throw new HttpPrasingxception(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                }
                continue;
            }

            if (!methodParsed) {
                request.setMethod(buffer.toString());
                methodParsed = true;
            } else if (!targetParsed) {
                request.setTarget(buffer.toString());
                targetParsed = true;
            } else {
                request.setHttpVersion(buffer.toString());
                versionParsed = true;
            }

            buffer.delete(0, buffer.length());
        }
    }

    private void parseStatusLine(BufferedReader input, HttpRequest request) {
    }

}
