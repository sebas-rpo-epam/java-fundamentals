package org.epamcampus.dp.structural.adapter.pattern;

import org.epamcampus.dp.structural.adapter.domain.PaymentResult;
import org.epamcampus.dp.structural.adapter.domain.Money;

public interface PaymentGateway {
    PaymentResult charge(String customerId, Money amount);
    String gatewayName();
}
