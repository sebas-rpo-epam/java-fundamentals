package org.epamcampus.df.jackson.treemodel;

import com.fasterxml.jackson.databind.*;

public class DynamicJsonExample {

    public static void run() {
        System.out.println("\n--- Jackson Tree Model (JsonNode) ---");

        String json = """
                {
                    "user": {
                        "name": "Alice",
                        "age": 30,
                        "roles": ["admin", "developer"]
                    },
                    "items": [
                        {"id": 1, "label": "Laptop"},
                        {"id": 2, "label": "Mouse"},
                        {"id": 3, "label": "Keyboard"}
                    ]
                }
                """;

        try {
            ObjectMapper mapper = new ObjectMapper();

            // 1. Parse with readTree — no predefined class needed
            JsonNode root = mapper.readTree(json);

            // 2. Navigate the tree
            String userName = root.get("user").get("name").asText();
            int userAge = root.get("user").get("age").asInt();
            System.out.println("user.name = " + userName);
            System.out.println("user.age  = " + userAge);

            // 3. Safe navigation for missing fields
            boolean missing = root.path("missingField").isMissingNode();
            System.out.println("root.path(\"missingField\").isMissingNode() = " + missing);

            // 4. Iterate roles array
            System.out.println("user.roles:");
            root.get("user").get("roles").forEach(r -> System.out.println("  - " + r.asText()));

            // 5. Iterate items array
            System.out.println("items:");
            root.get("items").forEach(item ->
                    System.out.println("  id=" + item.get("id").asInt() + ", label=" + item.get("label").asText()));

            System.out.println("JsonNode = no predefined class needed. Safe for dynamic/unknown schemas.");

        } catch (Exception e) {
            throw new RuntimeException("Tree model example failed", e);
        }
    }
}
