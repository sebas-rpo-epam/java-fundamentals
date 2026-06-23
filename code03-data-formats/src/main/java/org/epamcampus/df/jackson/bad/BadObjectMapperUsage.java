package org.epamcampus.df.jackson.bad;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epamcampus.df.jackson.pojo.UserDto;

// ANTI-PATTERN: Creating a new ObjectMapper for each operation.
// ObjectMapper is expensive to initialize (registers modules, configures features).
// In a server that handles 1000 req/sec this wastes significant CPU and memory.
public class BadObjectMapperUsage {

    public static void demonstrate() {
        System.out.println("\n--- BAD: new ObjectMapper() per call ---");
        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            try {
                ObjectMapper mapper = new ObjectMapper(); // new instance each time
                UserDto user = new UserDto("Alice", 30, "alice@epam.com");
                mapper.writeValueAsString(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        long badMs = (System.nanoTime() - start) / 1_000_000;
        System.out.println("100 serializations with new ObjectMapper() each: " + badMs + "ms");

        // Compare with singleton
        start = System.nanoTime();
        ObjectMapper singleton = new ObjectMapper();
        for (int i = 0; i < 100; i++) {
            try {
                UserDto user = new UserDto("Alice", 30, "alice@epam.com");
                singleton.writeValueAsString(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        long goodMs = (System.nanoTime() - start) / 1_000_000;
        System.out.println("100 serializations with singleton ObjectMapper: " + goodMs + "ms");
        System.out.println("-> Singleton is significantly faster. Reuse ObjectMapper always.");
    }
}
