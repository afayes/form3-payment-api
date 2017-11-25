package com.form3.payment;

import static com.form3.payment.PaymentController.MEDIA_TYPE_HAL_JSON_VALUE;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo add comments.
 */
@RestController
@RequestMapping(value = Application.VERSION + PaymentController.URL, produces = MEDIA_TYPE_HAL_JSON_VALUE)
public class PaymentController {

    public static final String URL = "/payments";

    public static final String MEDIA_TYPE_HAL_JSON_VALUE = "application/hal+json";

        @RequestMapping(method = RequestMethod.PUT, value = "/{paymentId}")
        @ResponseBody
        public ResponseEntity<Payment> createOrUpdatePayment(@PathVariable("paymentId") final UUID paymentId)  {
            return null;
        }
}
