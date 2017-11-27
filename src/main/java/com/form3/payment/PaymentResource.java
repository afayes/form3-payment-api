package com.form3.payment;

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
