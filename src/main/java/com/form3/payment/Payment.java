package com.form3.payment;

import java.util.UUID;

/**
 * todo add comments.
 */
public class Payment {

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Payment))
            return false;

        final Payment payment = (Payment) o;

        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
