package org.epamcampus.dp.creational.builder.domain;

import java.util.Map;

public record HttpRequest(
        String method,
        String url,
        String body,
        Map<String, String> headers,
        int timeoutMs,
        String authToken,
        int retries
) {}
