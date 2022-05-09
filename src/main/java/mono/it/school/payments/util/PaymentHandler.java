package mono.it.school.payments.util;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.entity.PaymentEntity;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableAsync
public class PaymentHandler {

    private final Random random;
    private final PaymentService paymentService;

    @Autowired
    public PaymentHandler(Random random, PaymentService paymentService) {
        this.random = random;
        this.paymentService = paymentService;
    }

    @Async
    public void handleNewPayment() {
        List<Payment> newPayments = paymentService.getByStatus(PaymentStatus.NEW.name());
        List<Payment> handledPayments = new ArrayList<>();

        for (Payment payment : newPayments) {
            Payment handledPayment = generateNewStatus(payment);
            if (handledPayment.getPaymentStatus() != PaymentStatus.NEW) {
                handledPayments.add(handledPayment);
            }
        }
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
