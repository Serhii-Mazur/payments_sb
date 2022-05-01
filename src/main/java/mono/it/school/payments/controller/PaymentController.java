package mono.it.school.payments.controller;

import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/add")
    public String addPayment(
            @RequestParam("template_id") UUID templateID,
            @RequestParam("card_number") long cardNumber,
            @RequestParam("payment_amount") float paymentAmount
    ) {

        return "payment/add";
    }
}
