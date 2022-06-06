package mono.it.school.payments.api;

import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.api.dto.ResponsePaymentDto;
import mono.it.school.payments.api.dto.RequestPaymentDto;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.mapper.PaymentMapper;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final StopWatch timeMeasure;

    @Autowired
    public PaymentController(PaymentService paymentService, StopWatch timeMeasure) {
        this.paymentService = paymentService;
        this.timeMeasure = timeMeasure;
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation("Get all Payments")
    public List<ResponsePaymentDto> getAllPayments() {

        return paymentService.getAll()
                .stream()
                .map(PaymentMapper::paymentToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    @ResponseBody
    @SneakyThrows
    @ApiOperation("Add new Payment")
    public ResponsePaymentDto addPayment(@RequestBody @Valid RequestPaymentDto paymentDto,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid Payment: {}", paymentDto);
            throw new InvalidEntityException("Invalid Payment One or more fields do not match the requirements.");
        } else {
            timeMeasure.start("savingPayment");
            Payment savedPayment = paymentService.save(PaymentMapper.requestDtoToPayment(paymentDto));
            timeMeasure.stop();
            log.info("Payment saved. {}\nOperation time: {} ms", savedPayment, timeMeasure.getLastTaskTimeMillis());

            return PaymentMapper.paymentToResponseDto(savedPayment);
        }
    }
}
