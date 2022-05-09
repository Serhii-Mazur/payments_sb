package mono.it.school.payments.util;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log4j2
@Service
@EnableAsync
public class PaymentProcessor {

    private final Random random;
    private final PaymentService paymentService;

    @Autowired
    public PaymentProcessor(Random random, PaymentService paymentService) {
        this.random = random;
        this.paymentService = paymentService;
    }

    @Scheduled(cron = "${cron.everysecond.bankdays}")
    public void execute() {
//        System.out.println("Thread: " + Thread.currentThread().getName());

        handleNewPayments();
    }

    @Async
    public List<Payment> handleNewPayments() {
        List<Payment> newPayments = paymentService.getByStatus(PaymentStatus.NEW.name());
        List<Payment> handledPayments = new ArrayList<>();

        for (Payment payment : newPayments) {
            Payment handledPayment = generateNewStatus(payment);
            if (handledPayment.getPaymentStatus() != PaymentStatus.NEW) {
                handledPayments.add(paymentService.save(handledPayment));
            }
        }

        return handledPayments;
    }

    private Payment generateNewStatus(Payment payment) {
        Payment result = payment;
        LocalDateTime createdDateTime = payment.getCreatedDateTime();
        long lag = ChronoUnit.SECONDS.between(createdDateTime, LocalDateTime.now().withNano(0));

        if (lag > 2) {
            int status = random.nextInt(3);
            switch (status) {
                case 1:
                    result = new Payment(payment.getPaymentID(),
                            payment.getTemplateID(),
                            payment.getCardNumber(),
                            payment.getPaymentAmount(),
                            PaymentStatus.DONE,
                            createdDateTime,
                            null);
                    break;
                case 2:
                    result = new Payment(payment.getPaymentID(),
                            payment.getTemplateID(),
                            payment.getCardNumber(),
                            payment.getPaymentAmount(),
                            PaymentStatus.FAILED,
                            createdDateTime,
                            null);;
                    break;
            }
        }
        return result;
    }
}
