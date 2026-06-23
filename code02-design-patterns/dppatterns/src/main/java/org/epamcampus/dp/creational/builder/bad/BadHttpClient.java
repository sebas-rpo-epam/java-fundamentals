package org.epamcampus.dp.creational.builder.bad;

import org.epamcampus.dp.creational.builder.domain.HttpRequest;

import java.util.Map;

/**
 * ❌ PROBLEM: Telescoping constructor — 7 params, nobody knows what arg 4 is.
 * Adding one more option requires a new constructor overload.
 */
public class BadHttpClient {

    public void demonstrateProblem() {
        String url = "https://api.epam.com/v1/users";
        String body = "{\"name\": \"Alice\"}";

        // Who can read this? What is arg 4? arg 6? arg 7?
        HttpRequest request = new HttpRequest("POST", url, body, Map.of(), 5000, null, 1);
        System.out.println("  BAD: new HttpRequest(\"POST\", url, body, Map.of(), 5000, null, 1)");
        System.out.println("       → What is arg 4? arg 6? This is unreadable.");
        System.out.println("       → Created: " + request.method() + " " + request.url());
    }
}
