package com.form3.payment;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * todo add comments.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean paymentExists(final UUID paymentId) {
        return paymentRepository.exists(paymentId);
    }

    @Override
    public Payment savePayment(final Payment payment) {
        return paymentRepository.save(payment);
    }
}
