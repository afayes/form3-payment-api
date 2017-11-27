package com.form3.payment;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

/**
 * todo add comments.
 */
@Service
public class PaymentResourceAssembler extends ResourceAssemblerSupport<Payment, PaymentResource> {

    public static final String REL_CREATE_OR_UPDATE = "createOrUpdate";

    public PaymentResourceAssembler() {
        super(PaymentController.class, PaymentResource.class);
    }

    @Override
    public PaymentResource toResource(final Payment payment) {
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(PaymentController.class).createOrUpdatePayment(payment.getId(), payment)).withRel(REL_CREATE_OR_UPDATE));

        PaymentResource resource = createResourceWithId(payment.getId(), payment);
        resource.add(links);

        return resource;
    }

    @Override
    protected PaymentResource instantiateResource(final Payment entity) {
        return new PaymentResource(entity);
    }
}
