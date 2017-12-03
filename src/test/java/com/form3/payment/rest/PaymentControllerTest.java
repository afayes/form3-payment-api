package com.form3.payment.rest;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.form3.payment.TestUtils;
import com.form3.payment.model.Payment;
import com.form3.payment.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for {@link com.form3.payment.rest.PaymentController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

    private static final String FIELD_PAYMENT_SERVICE = "paymentService";

    private static final String FIELD_PAYMENT_RESOURCE_ASSEMBLER = "paymentResourceAssembler";

    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Mock
    private PaymentResourceAssembler paymentResourceAssembler;

    @Before
    public void setUp() throws Exception {
        paymentController = new PaymentController();
        TestUtils.setFieldReflectively(paymentController, FIELD_PAYMENT_SERVICE, paymentService);
        TestUtils.setFieldReflectively(paymentController, FIELD_PAYMENT_RESOURCE_ASSEMBLER, paymentResourceAssembler);
    }

    @Test
    public void test_save_payment_when_payment_does_not_exist_returns_201_with_payment_resource() throws Exception {
        test_save_payment_returns_correct_response(false, HttpStatus.CREATED);
    }

    @Test
    public void test_save_payment_when_payment_exists_returns_200_with_payment_resource() throws Exception {
        test_save_payment_returns_correct_response(true, HttpStatus.OK);
    }

    public void test_save_payment_returns_correct_response(boolean paymentExists, HttpStatus expectedHttpStatusResponse) throws Exception {
        Payment payment = TestUtils.getSamplePayment();

        when(paymentService.paymentExists(payment.getId())).thenReturn(paymentExists);
        when(paymentService.savePayment(payment)).thenReturn(payment);

        PaymentResource paymentResource = mock(PaymentResource.class);
        when(paymentResourceAssembler.toResource(payment)).thenReturn(paymentResource);


        ResponseEntity<PaymentResource> responseEntity = paymentController.savePayment(payment.getId(), payment);

        assertThat(responseEntity.getStatusCode()).isEqualTo(expectedHttpStatusResponse);
        assertThat(responseEntity.getBody()).isSameAs(paymentResource);
    }
}
