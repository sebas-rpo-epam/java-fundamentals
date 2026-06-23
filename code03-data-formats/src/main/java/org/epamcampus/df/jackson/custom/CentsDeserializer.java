package org.epamcampus.df.jackson.custom;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigDecimal;

public class CentsDeserializer extends StdDeserializer<BigDecimal> {

    public CentsDeserializer() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        // Read integer cents from JSON, convert to dollars
        int cents = p.getIntValue();
        return BigDecimal.valueOf(cents).divide(BigDecimal.valueOf(100));
    }
}
