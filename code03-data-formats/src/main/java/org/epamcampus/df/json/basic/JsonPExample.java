package org.epamcampus.df.json.basic;

import jakarta.json.*;
import java.io.StringReader;

public class JsonPExample {

    public static void run() {
        System.out.println("\n--- Jakarta JSON-P ---");
        // Jakarta JSON-P: standard but verbose. Jackson does this in 1 line.

        // 1. Build a JsonObject using Json.createObjectBuilder()
        JsonObject catalog = Json.createObjectBuilder()
                .add("catalogName", "EPAM Tech Store")
                .add("currency", "USD")
                .add("products", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("sku", "SKU-001")
                                .add("name", "Mechanical Keyboard")
                                .add("price", "149.99")
                                .add("inStock", true))
                        .add(Json.createObjectBuilder()
                                .add("sku", "SKU-002")
                                .add("name", "USB-C Hub")
                                .add("price", "49.99")
                                .add("inStock", false)))
                .build();

        // 2. Print the JSON string
        String jsonString = catalog.toString();
        System.out.println("Built JSON:");
        System.out.println(jsonString);

        // 3. Parse it back and read values
        JsonObject parsed = Json.createReader(new StringReader(jsonString)).readObject();
        String name = parsed.getString("catalogName");
        String currency = parsed.getString("currency");
        JsonArray products = parsed.getJsonArray("products");

        System.out.println("\nParsed back:");
        System.out.println("  catalogName: " + name);
        System.out.println("  currency: " + currency);
        System.out.println("  product count: " + products.size());
        products.forEach(p -> {
            JsonObject prod = (JsonObject) p;
            System.out.println("    " + prod.getString("sku") + " - " + prod.getString("name")
                    + " @ $" + prod.getString("price") + " inStock=" + prod.getBoolean("inStock"));
        });

        System.out.println("\nNote: Jakarta JSON-P: standard but verbose. Jackson does this in 1 line.");
    }
}
