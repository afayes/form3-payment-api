package com.form3.payment.rest;

import com.form3.payment.model.Payment;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * todo add comments.
 */
public class PaymentResource extends Resource<Payment> {

    public PaymentResource(final Payment content, final Link... links) {
        super(content, links);
    }
}
