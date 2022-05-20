package mono.it.school.payments.util;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class PaymentHandler {
    private final Random random;

    public PaymentHandler() {
        this.random = new Random();
    }

    public Payment execute(Payment payment) {
        LocalDateTime createdDateTime = payment.getCreatedDateTime();
        long lag = ChronoUnit.SECONDS.between(createdDateTime, LocalDateTime.now().withNano(0));

        payment.setPaymentStatus(getNewStatus(lag));

        return payment;
    }

    private PaymentStatus getNewStatus(long lag) {
        PaymentStatus result = PaymentStatus.NEW;

        if (lag > 2) {
            int status = random.nextInt(3);
            switch (status) {
                case 1:
                    result = PaymentStatus.DONE;
                    break;
                case 2:
                    result = PaymentStatus.FAILED;
                    break;
            }
        }
        return result;
    }
}
