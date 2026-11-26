package com.yo.elderlycarebooking.dto.payment;

import com.yo.elderlycarebooking.domain.enumm.PaymentMethodEnum;
import com.yo.elderlycarebooking.domain.enumm.PaymentTransactionStatusEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record PaymentResponse(
        Long paymentId,
        Long bookingId,
        String bookingCode,
        String transactionCode,
        BigDecimal amount,
        PaymentMethodEnum paymentMethod,
        PaymentTransactionStatusEnum status,
        Timestamp paidAt,
        String gatewayResponse,
        Timestamp createdAt
) {
}
