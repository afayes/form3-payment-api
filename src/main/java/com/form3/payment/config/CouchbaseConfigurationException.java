package com.form3.payment.config;

/**
 * Exception thrown when there is an error in {@link com.form3.payment.config.CouchbaseConfiguration}.
 */
public class CouchbaseConfigurationException extends RuntimeException {

    public CouchbaseConfigurationException(final String message) {
        super(message);
    }
}
