package org.epamcampus.dp.creational.builder.pattern;

import org.epamcampus.dp.creational.builder.domain.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {

    private String method;
    private String url;
    private String body;
    private final Map<String, String> headers = new HashMap<>();
    private int timeoutMs = 5000;
    private String authToken;
    private int retries = 1;

    private HttpRequestBuilder() {}

    public static HttpRequestBuilder newRequest() {
        return new HttpRequestBuilder();
    }

    public HttpRequestBuilder method(String method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public HttpRequestBuilder body(String body) {
        this.body = body;
        return this;
    }

    public HttpRequestBuilder header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpRequestBuilder timeout(int ms) {
        this.timeoutMs = ms;
        return this;
    }

    public HttpRequestBuilder auth(String token) {
        this.authToken = token;
        return this;
    }

    public HttpRequestBuilder retries(int n) {
        this.retries = n;
        return this;
    }

    public HttpRequest build() {
        if (method == null || method.isBlank()) {
            throw new IllegalStateException("HTTP method is required");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("URL is required");
        }
        return new HttpRequest(method, url, body, Map.copyOf(headers), timeoutMs, authToken, retries);
    }
}
