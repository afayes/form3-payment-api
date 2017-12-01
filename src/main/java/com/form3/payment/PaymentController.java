package com.form3.payment;

import static com.form3.payment.PaymentController.MEDIA_TYPE_HAL_JSON_VALUE;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<PaymentResource> savePayment(@PathVariable("paymentId") final UUID paymentId, @RequestBody final Payment payment) {
        payment.setId(paymentId);
        boolean paymentExists = paymentService.paymentExists(paymentId);

        Payment paymentPersisted = paymentService.savePayment(payment);
        HttpStatus status = paymentExists ? HttpStatus.OK : HttpStatus.CREATED;

        PaymentResource paymentResource = paymentResourceAssembler.toResource(paymentPersisted);
        return ResponseEntity.status(status).body(paymentResource);
    }

    @RequestMapping(value = URL_PAYMENT_RESOURCE)
    @ResponseBody
    public ResponseEntity<PaymentResource> getPayment(@PathVariable("paymentId") final UUID paymentId) {
        Optional<Payment> payment = paymentService.getPayment(paymentId);
        if (payment.isPresent()) {
            PaymentResource paymentResource = paymentResourceAssembler.toResource(payment.get());
            return ResponseEntity.ok().body(paymentResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = URL_PAYMENT_RESOURCE)
    @ResponseBody
    public ResponseEntity<PaymentResource> deletePayment(@PathVariable("paymentId") final UUID paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping()
    @ResponseBody
    public ResponseEntity<List<PaymentResource>> getPayments() {
        List<Payment> payments = paymentService.getPayments();
        List<PaymentResource> paymentResources = paymentResourceAssembler.toResources(payments);
        return ResponseEntity.ok().body(paymentResources);
    }
}
