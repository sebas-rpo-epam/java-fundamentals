package org.epamcampus.df.jackson.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.math.BigDecimal;

public class MoneySerializer extends StdSerializer<BigDecimal> {

    public MoneySerializer() {
        super(BigDecimal.class);
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // Convert dollars to integer cents for the wire format
        int cents = value.multiply(BigDecimal.valueOf(100)).intValue();
        gen.writeNumber(cents);
    }
}
