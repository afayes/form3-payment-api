/*
 * All rights reserved. Copyright (c) Ixxus Ltd 2017
 */

package com.form3.payment;

import java.util.Optional;
import java.util.UUID;

/**
 * todo add comments.
 */
public interface PaymentService {

    /**
     * todo add comments.
     */
    boolean paymentExists(UUID paymentId);

    /**
     * todo add comments.
     */
    Payment savePayment(final Payment payment);

    Optional<Payment> getPayment(final UUID paymentId);

    void deletePayment(final UUID paymentId);
}
