package com.form3.payment.service;

import java.util.UUID;

import com.form3.payment.model.Payment;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * todo add comments.
 */
@ViewIndexed(designDoc = "payment")
@N1qlPrimaryIndexed
@N1qlSecondaryIndexed(indexName = "paymentsSecondary")
@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {

}
