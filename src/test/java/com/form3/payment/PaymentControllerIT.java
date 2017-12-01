package com.form3.payment;

import static com.form3.payment.rest.PaymentResourceAssembler.REL_DELETE;
import static com.form3.payment.rest.PaymentResourceAssembler.REL_SAVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import com.form3.payment.model.Payment;
import com.form3.payment.rest.PaymentController;
import com.form3.payment.service.PaymentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class
PaymentControllerIT {

    private static final String PROPERTY_HREF = "href";

    private static final String PROPERTY_LINKS = "links";

    private static final String PROPERTY_LINKS_WITH_UDNERSCORE = "_links";

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
        // clean out the database so we have a clean state on each test run
        paymentRepository.deleteAll();
    }

    @Test
    public void test_save_payment_when_payment_does_not_exist_returns_201_with_payment_resource() {
        Payment paymentToCreate = TestUtils.getSamplePayment();

        ResponseEntity<PaymentResourceResponseWithLinksAsMap> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate),
                PaymentResourceResponseWithLinksAsMap.class, paymentToCreate.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        PaymentResourceResponseWithLinksAsMap paymentResourceResponseWithLinksAsMap = response.getBody();
        assertThat(paymentResourceResponseWithLinksAsMap).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS_WITH_UDNERSCORE);

        assertThat(paymentResourceResponseWithLinksAsMap._links).isEqualTo(getPaymentResourceLinksAsMap(paymentToCreate.getId()));

    }

    @Test
    public void test_save_payment_when_payment_exists_returns_200_with_payment_resource() {
        Payment paymentToCreate = TestUtils.getSamplePayment();

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponseWithLinksAsMap.class,
                paymentToCreate.getId());

        ResponseEntity<PaymentResourceResponseWithLinksAsMap> response = template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate),
                PaymentResourceResponseWithLinksAsMap.class, paymentToCreate.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PaymentResourceResponseWithLinksAsMap paymentResourceResponseWithLinksAsMap = response.getBody();
        assertThat(paymentResourceResponseWithLinksAsMap).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS_WITH_UDNERSCORE);
        assertThat(paymentResourceResponseWithLinksAsMap._links).isEqualTo(getPaymentResourceLinksAsMap(paymentToCreate.getId()));
    }

    @Test
    public void test_get_payment_when_payment_exists_returns_200_with_payment_resource() {
        Payment paymentToCreate = TestUtils.getSamplePayment();

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponseWithLinksAsMap.class,
                paymentToCreate.getId());

        ResponseEntity<PaymentResourceResponseWithLinksAsMap> response = template
                .getForEntity(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, PaymentResourceResponseWithLinksAsMap.class, paymentToCreate.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PaymentResourceResponseWithLinksAsMap paymentResourceResponseWithLinksAsMap = response.getBody();
        assertThat(paymentResourceResponseWithLinksAsMap).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS_WITH_UDNERSCORE);
        assertThat(paymentResourceResponseWithLinksAsMap._links).isEqualTo(getPaymentResourceLinksAsMap(paymentToCreate.getId()));
    }

    @Test
    public void test_get_payment_when_payment_does_not_exist_returns_404() {
        UUID nonExistentPaymentId = UUID.randomUUID();
        ResponseEntity<PaymentResourceResponseWithLinksAsMap> response = template
                .getForEntity(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, PaymentResourceResponseWithLinksAsMap.class, nonExistentPaymentId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void test_delete_payment_when_payment_exists_returns_204() {
        Payment paymentToCreate = TestUtils.getSamplePayment();

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponseWithLinksAsMap.class,
                paymentToCreate.getId());

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

    @Test
    public void test_get_payments_when_one_payment_exists_returns_200_with_payment_resource() {
        Payment paymentToCreate = TestUtils.getSamplePayment();

        template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(paymentToCreate), PaymentResourceResponseWithLinksAsMap.class,
                paymentToCreate.getId());

        ParameterizedTypeReference<List<PaymentResourceResponseWithLinksAsList>> parameterizedTypeReference = new ParameterizedTypeReference<List<PaymentResourceResponseWithLinksAsList>>() {
        };

        ResponseEntity<List<PaymentResourceResponseWithLinksAsList>> response = template.exchange(baseUrl, HttpMethod.GET, null, parameterizedTypeReference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PaymentResourceResponseWithLinksAsList> paymentResources = response.getBody();
        assertThat(paymentResources.size()).isEqualTo(1);

        PaymentResourceResponseWithLinksAsList paymentResourceRetrieved = paymentResources.get(0);
        assertThat(paymentResourceRetrieved).isEqualToIgnoringGivenFields(paymentToCreate, PROPERTY_LINKS);
        assertThat(paymentResourceRetrieved.links).isEqualTo(getPaymentResourceLinksAsList(paymentToCreate.getId()));
    }

    @Test
    public void test_get_payments_when_multiple_payment_exists_returns_200_with_payment_resources() {
        List<Payment> paymentsToCreate = TestUtils.getSamplePayments();

        paymentsToCreate.stream().forEach(payment -> {
            template.exchange(baseUrl + PaymentController.URL_PAYMENT_RESOURCE, HttpMethod.PUT, new HttpEntity<Payment>(payment), PaymentResourceResponseWithLinksAsMap.class,
                    payment.getId());
        });

        ParameterizedTypeReference<List<PaymentResourceResponseWithLinksAsList>> parameterizedTypeReference = new ParameterizedTypeReference<List<PaymentResourceResponseWithLinksAsList>>() {
        };

        ResponseEntity<List<PaymentResourceResponseWithLinksAsList>> response = template.exchange(baseUrl, HttpMethod.GET, null, parameterizedTypeReference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PaymentResourceResponseWithLinksAsList> paymentResources = response.getBody();
        assertThat(paymentResources.size()).isEqualTo(paymentsToCreate.size());

        paymentsToCreate.stream().forEach(expectedPayment -> {
            PaymentResourceResponseWithLinksAsList paymentResourceRetrieved = paymentResources.stream().filter(resource -> resource.getId().equals(expectedPayment.getId())).findFirst().get();
            assertThat(paymentResourceRetrieved).isEqualToIgnoringGivenFields(expectedPayment, PROPERTY_LINKS);
            assertThat(paymentResourceRetrieved.links).isEqualTo(getPaymentResourceLinksAsList(expectedPayment.getId()));
        } );
    }

    @Test
    public void test_get_payments_when_no_payment_exist_returns_200_with_empty_list() {
        ParameterizedTypeReference<List<PaymentResourceResponseWithLinksAsList>> parameterizedTypeReference = new ParameterizedTypeReference<List<PaymentResourceResponseWithLinksAsList>>() {
        };

        ResponseEntity<List<PaymentResourceResponseWithLinksAsList>> response = template.exchange(baseUrl, HttpMethod.GET, null, parameterizedTypeReference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PaymentResourceResponseWithLinksAsList> paymentResources = response.getBody();
        assertThat(paymentResources.size()).isEqualTo(0);
    }


    /**
     * NOTE: Single resource HATEOAS response come back in the form represented by this class. The response format for links changed after adding SWAGGER to the POM, not sure why.
     * See {@link PaymentResourceResponseWithLinksAsList} for the original format.
     */
    private static class PaymentResourceResponseWithLinksAsMap extends Payment {
        private Map<String, Map<String, String>> _links;

        public Map<String, Map<String, String>> get_links() {
            return _links;
        }

        public void set_links(final Map<String, Map<String, String>> _links) {
            this._links = _links;
        }
    }

    /**
     * NOTE: List of resource HATEOAS response come back in the form represented by this class. Not sure why Spring is returning a different response
     * format to the single resource response.
     */
    private static class PaymentResourceResponseWithLinksAsList extends Payment {
        private List<Link> links;

        public List<Link> getLinks() {
            return links;
        }

        public void setLinks(final List<Link> links) {
            this.links = links;
        }

        private static class Link {
            private String rel;

            private String href;

            public Link() {
            }

            public Link(final String rel, final String href) {
                this.rel = rel;
                this.href = href;
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

            @Override
            public boolean equals(final Object o) {
                if (this == o)
                    return true;
                if (!(o instanceof Link))
                    return false;

                final Link link = (Link) o;

                if (!getRel().equals(link.getRel()))
                    return false;
                return href.equals(link.href);
            }

            @Override
            public int hashCode() {
                int result = getRel().hashCode();
                result = 31 * result + href.hashCode();
                return result;
            }
        }
    }

    private Map<String, Map<String, String>> getPaymentResourceLinksAsMap(final UUID paymentId) {
        final String resourceUrl = baseUrl + "/" + paymentId;

        final Map<String, Map<String, String>> links = new HashMap<>();

        final Map<String, String> resourceLink = new HashMap<>();
        resourceLink.put(PROPERTY_HREF, resourceUrl);

        links.put(REL_SELF, resourceLink);
        links.put(REL_SAVE, resourceLink);
        links.put(REL_DELETE, resourceLink);

        return links;
    }

    private List<PaymentResourceResponseWithLinksAsList.Link> getPaymentResourceLinksAsList(final UUID paymentId) {
        final String resourceUrl = baseUrl + "/" + paymentId;

        List<PaymentResourceResponseWithLinksAsList.Link> links = Arrays.asList(
                new PaymentResourceResponseWithLinksAsList.Link(REL_SELF, resourceUrl),
                new PaymentResourceResponseWithLinksAsList.Link(REL_SAVE, resourceUrl),
                new PaymentResourceResponseWithLinksAsList.Link(REL_DELETE, resourceUrl)
        );

        return links;
    }
}
