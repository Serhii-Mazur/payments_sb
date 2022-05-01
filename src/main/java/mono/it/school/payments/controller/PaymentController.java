package mono.it.school.payments.controller;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.service.PaymentService;
import mono.it.school.payments.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final TemplateService templateService;

    @Autowired
    public PaymentController(PaymentService paymentService, TemplateService templateService) {
        this.paymentService = paymentService;
        this.templateService = templateService;
    }

    @PostMapping("/payment/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addPayment(@RequestParam("template_name") String templateName,
                           @RequestParam("card_number") long cardNumber,
                           @RequestParam("payment_amount") float paymentAmount
    ) {

        paymentService.save(new Payment(null,
                templateService.getByTemplateName(templateName).getTemplateID(),
                cardNumber,
                paymentAmount,
                PaymentStatus.NEW,
                null,
                null
        ));
    }
}
