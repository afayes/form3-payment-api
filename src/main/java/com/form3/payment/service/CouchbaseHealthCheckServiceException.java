package com.form3.payment.service;

/**
 * An exception thrown when there is an error in {@link com.form3.payment.service.CouchbaseHealthCheckService}.
 */
public class CouchbaseHealthCheckServiceException extends RuntimeException {

    public CouchbaseHealthCheckServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
