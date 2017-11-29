package com.form3.payment;

import static com.form3.payment.PaymentResourceAssembler.REL_SAVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.ArrayList;
import java.util.List;
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
        assertThat(paymentResourceResponse).isEqualToIgnoringGivenFields(paymentToCreate, "links");
        assertThat(paymentResourceResponse.links).isEqualTo(getPaymentResourceLinks(paymentToCreate.getId()));

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
        assertThat(paymentResourceResponse).isEqualToIgnoringGivenFields(paymentToCreate, "links");
        assertThat(paymentResourceResponse.links).isEqualTo(getPaymentResourceLinks(paymentToCreate.getId()));
    }

    private static class PaymentResourceResponse extends Payment {
	    private List<ResourceLink> links;

        public List<ResourceLink> getLinks() {
            return links;
        }

        public void setLinks(final List<ResourceLink> links) {
            this.links = links;
        }
    }

    private static class ResourceLink {
	    private String rel;

	    private String href;

        public ResourceLink() {
        }

        public ResourceLink(final String rel, final String href) {
            this.rel = rel;
            this.href = href;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o)
                return true;
            if (!(o instanceof ResourceLink))
                return false;

            final ResourceLink that = (ResourceLink) o;

            if (!getRel().equals(that.getRel()))
                return false;
            return getHref().equals(that.getHref());
        }

        @Override
        public int hashCode() {
            int result = getRel().hashCode();
            result = 31 * result + getHref().hashCode();
            return result;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(final String rel) {
            this.rel = rel;
        }

        public String getHref() {
            return href;
        }

        public void setHref(final String href) {
            this.href = href;
        }
    }

    private List<ResourceLink> getPaymentResourceLinks(final UUID paymentId) {
	    List<ResourceLink> links = new ArrayList<>();

        String resourceUrl = baseUrl + "/" + paymentId;
        links.add(new ResourceLink(REL_SELF, resourceUrl));
        links.add(new ResourceLink(REL_SAVE, resourceUrl));
        // links.add(new ResourceLink(REL_DELETE, resourceUrl));

	    return links;
    }
}
