package mono.it.school.payments.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Payment> getAllPayments() {

        return paymentService.getAll();
    }

    @PostMapping("/add")
    @ResponseBody
    @SneakyThrows
    public Payment addPayment(@RequestBody @Valid Payment payment,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid Payment: {}", payment);
            throw new InvalidEntityException("Invalid Payment One or more fields do not match the requirements.");
        } else {
            //TODO: Measure the execution time
            Payment savedPayment = paymentService.save(payment);
            log.info("Payment saved. {}", savedPayment);

            return savedPayment;
        }
    }
}
