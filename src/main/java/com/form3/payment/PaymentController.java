package com.form3.payment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo add comments.
 */
@RestController
public class PaymentController {


    @RequestMapping(value = "/")
    public String index()  {
        return "Hello world!";
    }
}
