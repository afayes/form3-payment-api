package com.form3.payment;

import static com.form3.payment.PaymentController.MEDIA_TYPE_HAL_JSON_VALUE;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    public static final String MEDIA_TYPE_HAL_JSON_VALUE = "application/hal+json";

    public static final String URL = "/payments";

    public static final String URL_PAYMENT_RESOURCE = "/{paymentId}";

    @Inject
    private PaymentService paymentService;

    @Inject
    private PaymentResourceAssembler paymentResourceAssembler;

    @RequestMapping(method = RequestMethod.PUT, value = URL_PAYMENT_RESOURCE)
    @ResponseBody
    public ResponseEntity<PaymentResource> createOrUpdatePayment(@PathVariable("paymentId") final UUID paymentId, @RequestBody final Payment payment) {
        payment.setId(paymentId);
        boolean paymentExists = paymentService.paymentExists(paymentId);

        Payment paymentPersisted = paymentService.createOrUpdate(payment);
        HttpStatus status = paymentExists ? HttpStatus.OK : HttpStatus.CREATED;

        PaymentResource paymentResource = paymentResourceAssembler.toResource(paymentPersisted);
        return ResponseEntity.status(status).body(paymentResource);
    }
}
