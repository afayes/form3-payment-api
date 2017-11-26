/*
 * All rights reserved. Copyright (c) Ixxus Ltd 2017
 */

package com.form3.payment;

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
    Payment createOrUpdate(final Payment payment);
}
