package com.form3.payment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.form3.payment.model.Payment;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean paymentExists(final UUID paymentId) {
        return paymentRepository.exists(paymentId);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Payment savePayment(final Payment payment) {
        return paymentRepository.save(payment);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Optional<Payment> getPayment(final UUID paymentId) {
        return Optional.ofNullable(paymentRepository.findOne(paymentId));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void deletePayment(final UUID paymentId) {
        paymentRepository.delete(paymentId);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<Payment> getPayments() {
        Iterable<Payment> payments = paymentRepository.findAll();
        return Lists.newArrayList(payments);
    }
}
