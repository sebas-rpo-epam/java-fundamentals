package org.epamcampus.dp.creational.builder.demo;

import org.epamcampus.dp.creational.builder.bad.BadHttpClient;
import org.epamcampus.dp.creational.builder.domain.HttpRequest;
import org.epamcampus.dp.creational.builder.pattern.HttpRequestBuilder;

import java.util.UUID;

public class MainBuilder {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          PATTERN 1: BUILDER              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- BAD: Telescoping constructor (unreadable call site) ---");
        new BadHttpClient().demonstrateProblem();

        System.out.println("\n--- GOOD: Fluent Builder (self-documenting) ---");
        HttpRequest request = HttpRequestBuilder.newRequest()
                .method("POST")
                .url("https://api.epam.com/v1/users")
                .header("Content-Type", "application/json")
                .header("X-Request-ID", UUID.randomUUID().toString())
                .body("{\"name\": \"Alice\"}")
                .auth("Bearer eyJhbGciOiJIUzI1NiJ9...")
                .timeout(3000)
                .retries(2)
                .build();

        System.out.println("  Built request:");
        System.out.println("    Method:  " + request.method());
        System.out.println("    URL:     " + request.url());
        System.out.println("    Body:    " + request.body());
        System.out.println("    Headers: " + request.headers());
        System.out.println("    Timeout: " + request.timeoutMs() + "ms");
        System.out.println("    Auth:    " + request.authToken());
        System.out.println("    Retries: " + request.retries());

        System.out.println("\n--- Validation: missing URL triggers clear error ---");
        try {
            HttpRequestBuilder.newRequest()
                    .method("GET")
                    .url(null)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("  Caught expected error: " + e.getMessage());
        }

        try {
            HttpRequestBuilder.newRequest()
                    .url("https://api.epam.com/health")
                    .build(); // method missing
        } catch (IllegalStateException e) {
            System.out.println("  Caught expected error: " + e.getMessage());
        }

        System.out.println("\nKey insight: Builder separates object construction from representation." +
                " Each parameter is named at the call site — no more mystery arg #4.");
    }
}
