package org.epamcampus.df.jackson.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epamcampus.df.jackson.annotations.ApiUser;
import org.epamcampus.df.jackson.annotations.OrderResponse;
import org.epamcampus.df.jackson.bad.BadDateHandling;
import org.epamcampus.df.jackson.bad.BadGenericDeserialization;
import org.epamcampus.df.jackson.bad.BadObjectMapperUsage;
import org.epamcampus.df.jackson.core.ObjectMapperFactory;
import org.epamcampus.df.jackson.custom.Money;
import org.epamcampus.df.jackson.datetime.DateTimeExample;
import org.epamcampus.df.jackson.polymorphism.PolymorphismExample;
import org.epamcampus.df.jackson.pojo.ProductRecord;
import org.epamcampus.df.jackson.pojo.UserDto;
import org.epamcampus.df.jackson.treemodel.DynamicJsonExample;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MainJackson {
    public static void run() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("  Jackson — Serialización / Deserialización");
        System.out.println("─".repeat(50));

        ObjectMapper mapper = ObjectMapperFactory.get();

        // 1. ObjectMapper singleton config
        System.out.println("\n--- 1. ObjectMapperFactory (singleton, configured) ---");
        System.out.println("Singleton mapper: " + mapper.getClass().getSimpleName());

        // 2. Basic POJO serialize/deserialize
        System.out.println("\n--- 2. Basic POJO: UserDto ---");
        try {
            UserDto user = new UserDto("Alice", 30, "alice@epam.com");
            String userJson = mapper.writeValueAsString(user);
            System.out.println("Serialized: " + userJson);
            UserDto parsed = mapper.readValue(userJson, UserDto.class);
            System.out.println("Deserialized: " + parsed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n--- 2b. ProductRecord (with @JsonProperty, @JsonIgnore, @JsonFormat) ---");
        try {
            ProductRecord product = new ProductRecord(
                    UUID.randomUUID(), "Mechanical Keyboard", "INTERNAL-001",
                    LocalDate.of(2026, 1, 15), new BigDecimal("149.99"));
            String prodJson = mapper.writeValueAsString(product);
            System.out.println("Serialized (internalCode should be absent): " + prodJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 3. Annotations
        System.out.println("\n--- 3. Annotations: ApiUser (@JsonProperty, @JsonIgnore) ---");
        try {
            ApiUser apiUser = new ApiUser("alice_j", "secret123", "alice@epam.com");
            String apiJson = mapper.writeValueAsString(apiUser);
            System.out.println("Serialized (password must be absent): " + apiJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n--- 3b. Annotations: OrderResponse (@JsonInclude, @JsonAlias, @JsonFormat) ---");
        try {
            OrderResponse order = new OrderResponse("ORD-001", LocalDate.of(2026, 6, 18),
                    new BigDecimal("299.99"), null);
            String orderJson = mapper.writeValueAsString(order);
            System.out.println("Serialized (notes null -> absent): " + orderJson);

            // Deserialize with old field name via @JsonAlias
            String oldFormat = "{\"order_id\":\"ORD-002\",\"orderDate\":\"2026-06-18\",\"total\":99.99}";
            OrderResponse fromOld = mapper.readValue(oldFormat, OrderResponse.class);
            System.out.println("Deserialized with alias order_id: orderId=" + fromOld.getOrderId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 4. DateTime
        DateTimeExample.run();

        // 5. Polymorphism
        PolymorphismExample.run();

        // 6. Tree model
        DynamicJsonExample.run();

        // 7. Custom serializers
        System.out.println("\n--- 7. Custom Serializers: Money (cents on wire) ---");
        try {
            Money price = new Money(new BigDecimal("19.99"));
            String moneyJson = mapper.writeValueAsString(price);
            System.out.println("Money $19.99 serialized as: " + moneyJson);

            Money parsed = mapper.readValue("{\"amount\":1999}", Money.class);
            System.out.println("1999 cents deserialized as: " + parsed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 8. Bad patterns
        System.out.println("\n--- 8. Anti-patterns ---");
        BadObjectMapperUsage.demonstrate();
        BadDateHandling.demonstrate();
        BadGenericDeserialization.demonstrate();
    }
}
