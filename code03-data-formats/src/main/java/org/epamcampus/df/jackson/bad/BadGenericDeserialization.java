package org.epamcampus.df.jackson.bad;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.epamcampus.df.jackson.pojo.UserDto;
import java.util.List;
import java.util.Map;

public class BadGenericDeserialization {

    public static void demonstrate() {
        System.out.println("\n--- BAD: Generic deserialization without TypeReference ---");

        String json = "[{\"name\":\"Alice\",\"age\":30,\"email\":\"alice@epam.com\"}," +
                      "{\"name\":\"Bob\",\"age\":25,\"email\":\"bob@epam.com\"}]";

        ObjectMapper mapper = new ObjectMapper();

        try {
            // BAD: erased type — results in List<Map<String,Object>>, not List<UserDto>
            @SuppressWarnings("unchecked")
            List<Object> rawList = mapper.readValue(json, List.class);
            Object first = rawList.get(0);
            System.out.println("BAD - readValue(json, List.class):");
            System.out.println("  Type of first element: " + first.getClass().getName());
            System.out.println("  Is Map? " + (first instanceof Map));
            System.out.println("  Is UserDto? " + (first instanceof UserDto));
            System.out.println("  Value: " + first);

            // GOOD: TypeReference preserves generic type at runtime
            List<UserDto> typedList = mapper.readValue(json, new TypeReference<List<UserDto>>() {});
            Object firstTyped = typedList.get(0);
            System.out.println("\nGOOD - readValue(json, new TypeReference<List<UserDto>>() {}):");
            System.out.println("  Type of first element: " + firstTyped.getClass().getName());
            System.out.println("  Is UserDto? " + (firstTyped instanceof UserDto));
            System.out.println("  Value: " + firstTyped);

            System.out.println("\n-> Always use TypeReference<T> for generic collections.");

        } catch (Exception e) {
            throw new RuntimeException("BadGenericDeserialization demo failed", e);
        }
    }
}
