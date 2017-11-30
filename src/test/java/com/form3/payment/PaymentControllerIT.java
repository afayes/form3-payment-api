package com.form3.payment;

import static com.form3.payment.PaymentResourceAssembler.REL_SAVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

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

    private static final String PROPERTY_HREF = "href";

    private static final String PROPERTY_LINKS = "_links";

    private TestRestTemplate template;

	@Value("${local.server.port}")
	private int port;

	private String baseUrl;

    private static final String REL_SELF = "self";

    @Inject
    private PaymentRepository paymentRepository;

    @Before
	public void setUp() throws Exception {
		template = new TestRestTemplate();
		baseUrl = "http://localhost:" + port + Application.VERSION + PaymentController.URL;
	}

	@Test
	public void test_save_payment_when_payment_does_not_exist_returns_201_with_payment_resource() {
		Payment paymentToCreate = new Payment();
		paymentToCreate.setId(UUID.randomUUID());

        ResponseEntity<PaymentResourceResponse> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate),
                PaymentResourceResponse.class, paymentToCreate.getId());

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        PaymentResourceResponse paymentResourceResponse = response.getBody();
        assertThat(paymentResourceResponse).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS);

        assertThat(paymentResourceResponse._links).isEqualTo(getPaymentResourceLinks(paymentToCreate.getId()));

	}

    @Test
    public void test_save_payment_when_payment_exists_returns_200_with_payment_resource() {
        Payment paymentToCreate = new Payment();
        paymentToCreate.setId(UUID.randomUUID());

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponse.class, paymentToCreate.getId());

        ResponseEntity<PaymentResourceResponse> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate),
				PaymentResourceResponse.class, paymentToCreate.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PaymentResourceResponse paymentResourceResponse = response.getBody();
        assertThat(paymentResourceResponse).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS);
        assertThat(paymentResourceResponse._links).isEqualTo(getPaymentResourceLinks(paymentToCreate.getId()));
    }

    @Test
    public void test_get_payment_when_payment_exists_returns_200_with_payment_resource() {
        Payment paymentToCreate = new Payment();
        paymentToCreate.setId(UUID.randomUUID());

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponse.class, paymentToCreate.getId());

        ResponseEntity<PaymentResourceResponse> response = template.getForEntity(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, PaymentResourceResponse.class, paymentToCreate.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PaymentResourceResponse paymentResourceResponse = response.getBody();
        assertThat(paymentResourceResponse).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS);
        assertThat(paymentResourceResponse._links).isEqualTo(getPaymentResourceLinks(paymentToCreate.getId()));
    }

    @Test
    public void test_get_payment_when_payment_does_not_exist_returns_404() {
        UUID nonExistentPaymentId = UUID.randomUUID();
        ResponseEntity<PaymentResourceResponse> response = template.getForEntity(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, PaymentResourceResponse.class, nonExistentPaymentId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void test_delete_payment_when_payment_exists_returns_204() {
        Payment paymentToCreate = new Payment();
        paymentToCreate.setId(UUID.randomUUID());

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponse.class, paymentToCreate.getId());

        ResponseEntity<?> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.DELETE, null, Object.class, paymentToCreate.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void test_delete_payment_when_payment_does_not_exist_returns_204() {
        UUID nonExistentPaymetId = UUID.randomUUID();
        ResponseEntity<?> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.DELETE, null, Object.class, nonExistentPaymetId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    private static class PaymentResourceResponse extends Payment {
	    private Map<String, Map<String, String>> _links;

        public Map<String, Map<String, String>> get_links() {
            return _links;
        }

        public void set_links(final Map<String, Map<String, String>> _links) {
            this._links = _links;
        }
    }

    private Map<String, Map<String, String>> getPaymentResourceLinks(final UUID paymentId) {
        final String resourceUrl = baseUrl + "/" + paymentId;

        final Map<String, Map<String, String>> links = new HashMap<>();

        final Map<String, String> selfLink = new HashMap<>();
        selfLink.put(PROPERTY_HREF, resourceUrl);

        final Map<String, String> saveLink = new HashMap<>();
        saveLink.put(PROPERTY_HREF, resourceUrl);

        links.put(REL_SELF, selfLink);
        links.put(REL_SAVE, saveLink);

	    return links;
    }
}
