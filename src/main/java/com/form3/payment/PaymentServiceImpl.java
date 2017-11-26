package com.form3.payment;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * todo add comments.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    // todo replace with db
    private Set<Payment> payments;

    public PaymentServiceImpl() {
        this.payments = new HashSet<>();
    }

    @Override
    public Payment create(final Payment payment) {
        payments.add(payment);

        return payment;
    }
}
