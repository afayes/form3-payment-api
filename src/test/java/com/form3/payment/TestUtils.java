package com.form3.payment;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.form3.payment.model.Payment;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Test utility functions.
 */
public class TestUtils {

    private static final String SAMPLE_PAYMENTS_FILE = "sample_payments.json";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    private static final CollectionType LIST_OF_PAYMENT_TYPE = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Payment.class);

    public static Payment getSamplePayment() {
        Payment payment = parsePaymentsFromJsonFile().get(0);
        // change payment ID to make sure it is a unique payment each time
        payment.setId(UUID.randomUUID());
        return payment;
    }

    public static List<Payment> getSamplePayments() {
        List<Payment> payments = parsePaymentsFromJsonFile();
        // change payment ID to make sure it is a unique payment each time
        payments.stream().forEach(payment -> payment.setId(UUID.randomUUID()));
        return payments;
    }

    private static List<Payment> parsePaymentsFromJsonFile() {
        try {
            try(InputStream resourceAsStream = TestUtils.class.getClassLoader().getResourceAsStream(SAMPLE_PAYMENTS_FILE)) {
                String paymentsJson = IOUtils.toString(resourceAsStream, Charset.defaultCharset());
                List<Payment> payments = OBJECT_MAPPER.readValue(paymentsJson, LIST_OF_PAYMENT_TYPE);

                return payments;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse payment from json file " + SAMPLE_PAYMENTS_FILE + ":" + e.getMessage(), e);
        }
    }

    public static void setFieldReflectively(final Object targetObject, final String fieldName, final Object value) {
        Field field = ReflectionUtils.findField(targetObject.getClass(), fieldName);
        field.setAccessible(true);
        ReflectionUtils.setField(field, targetObject, value);
    }
}
