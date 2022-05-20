package mono.it.school.payments.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.util.PaymentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping(value = "/payment")
public class PaymentHandlingController {

    private final PaymentHandler paymentHandler;

    @Autowired
    public PaymentHandlingController(PaymentHandler paymentHandler) {
        this.paymentHandler = paymentHandler;
    }


    @PostMapping(value = "/handle")
    @ResponseBody
    @ApiOperation("Endpoint of PaymentHandler (separate web-service imitation)")
    public Payment handlePayment(@RequestBody Payment payment) {
        log.info("Request to handle payment {} has got", payment);
        return paymentHandler.execute(payment);
    }
}
