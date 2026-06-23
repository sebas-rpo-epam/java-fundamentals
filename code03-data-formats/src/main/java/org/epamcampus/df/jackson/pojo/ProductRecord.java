package org.epamcampus.df.jackson.pojo;

import com.fasterxml.jackson.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductRecord(
        @JsonProperty("product_id") UUID id,
        String name,
        @JsonIgnore String internalCode,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate releasedAt,
        BigDecimal price
) {}
