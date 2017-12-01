package com.form3.payment.rest;

import com.form3.payment.model.Payment;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * HATEOAS Payment resource wrapping a {@link com.form3.payment.model.Payment}.
 */
public class PaymentResource extends Resource<Payment> {

    public PaymentResource(final Payment content, final Link... links) {
        super(content, links);
    }
}
