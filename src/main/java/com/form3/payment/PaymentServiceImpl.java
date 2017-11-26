package com.form3.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * todo add comments.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    // todo replace with db
    private Map<UUID, Payment> payments;

    public PaymentServiceImpl() {
        this.payments = new HashMap<>();
    }

    @Override
    public boolean paymentExists(final UUID paymentId) {
        return payments.containsKey(paymentId);
    }

    @Override
    public Payment createOrUpdate(final Payment payment) {
        payments.put(payment.getId(), payment);

        return payment;
    }
}
