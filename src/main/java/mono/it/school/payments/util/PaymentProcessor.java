package mono.it.school.payments.util;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@EnableAsync
public class PaymentProcessor {

    private final String STATUS_GENERATOR_URL = "http://localhost:8080/payment/handle";
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    @Autowired
    public PaymentProcessor(PaymentService paymentService, RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    @Scheduled(cron = "${cron.every.sec.bankdays}")
    public void execute() {
        handleNewPayments();
    }

    synchronized public List<Payment> handleNewPayments() {
        List<Payment> newPayments = paymentService.getByStatus(PaymentStatus.NEW);
        List<Payment> handledPayments = new ArrayList<>();

        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        for (Payment payment : newPayments) {
            HttpEntity<String> httpEntity = new HttpEntity(payment, httpHeaders);
            Payment result = restTemplate.postForObject(STATUS_GENERATOR_URL, httpEntity, Payment.class);
            if (!result.getPaymentStatus().equals(PaymentStatus.NEW)) {
                handledPayments.add(paymentService.update(result));
            }
        }

        return handledPayments;
    }
}
