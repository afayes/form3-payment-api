package com.form3.payment;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT )
public class
PaymentControllerIT {

	private TestRestTemplate template;

	@Value("${local.server.port}")
	private int port;

	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		template = new TestRestTemplate();
		baseUrl = "http://localhost:" + port + Application.VERSION + PaymentController.URL;
	}

	@Test
	public void test_put_payment_when_payment_does_not_exist_returns_201_with_payment() {
		Payment paymentToCreate = new Payment();
		paymentToCreate.setId(UUID.randomUUID());
		ResponseEntity<Payment> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), Payment.class,
				paymentToCreate.getId());
		// todo use assert4j
		assertEquals("Status does not match", HttpStatus.CREATED, response.getStatusCode());
		assertEquals("payment do not match", paymentToCreate, response.getBody());
	}

    @Test
    public void test_put_payment_when_payment_exists_returns_200_with_payment() {
        Payment paymentToCreate = new Payment();
        paymentToCreate.setId(UUID.randomUUID());

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), Payment.class, paymentToCreate.getId());

        ResponseEntity<Payment> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), Payment.class,
				paymentToCreate.getId());
        // todo use assert4j
        assertEquals("Status does not match", HttpStatus.OK, response.getStatusCode());
        assertEquals("payment do not match", paymentToCreate, response.getBody());
    }

}
