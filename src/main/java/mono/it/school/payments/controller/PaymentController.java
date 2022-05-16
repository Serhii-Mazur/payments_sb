package mono.it.school.payments.controller;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.service.AddressService;
import mono.it.school.payments.service.PaymentService;
import mono.it.school.payments.service.TemplateService;
import mono.it.school.payments.validation.PaymentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    private final AddressService addressService;
    private final PaymentService paymentService;
    private final TemplateService templateService;

    @Autowired
    public PaymentController(AddressService addressService, PaymentService paymentService, TemplateService templateService) {
        this.addressService = addressService;
        this.paymentService = paymentService;
        this.templateService = templateService;
    }

    @GetMapping("/all")
    @ResponseBody
    public void getAllPayments(Model model) {
        model.addAttribute("allPayments", paymentService.getAll());
    }

    @PostMapping("/add")
    @ResponseBody
    public void addPayment(@RequestParam("address") String address,
                           @RequestParam("template_name") String templateName,
                           @RequestParam("description") String description,
                           @RequestParam("card_number") long cardNumber,
                           @RequestParam("payment_amount") float paymentAmount
    ) {
        UUID addressID = addressService.getByAddress(address).getAddressID();
        UUID templateID = templateService.getByTemplateNameAndAddressID(templateName, addressID).getTemplateID();

        Payment payment = new Payment(null,
                description,
                templateID,
                cardNumber,
                paymentAmount,
                PaymentStatus.NEW,
                null,
                null);

        if (PaymentValidation.validate(payment)) {
            paymentService.save(payment);
        }
    }
}
