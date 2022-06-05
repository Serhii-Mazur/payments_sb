package mono.it.school.payments.service;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Log4j2
@Service
public class PaymentHandler {

    private final Random random;
    private final StopWatch timeMeasure;

    public PaymentHandler(StopWatch timeMeasure) {
        this.timeMeasure = timeMeasure;
        this.random = new Random();
    }

    public Payment execute(Payment payment) {
        LocalDateTime createdDateTime = payment.getCreatedDateTime();
        long lag = ChronoUnit.SECONDS.between(createdDateTime, LocalDateTime.now().withNano(0));
        timeMeasure.start("gettingNewPaymentStatus");
        payment.setPaymentStatus(getNewStatus(lag));
        timeMeasure.stop();
        if (payment.getPaymentStatus().equals(PaymentStatus.NEW)) {
            log.info("Payment {} did not receive a new status .\nOperation time: {} ms", payment, timeMeasure.getLastTaskTimeMillis());
        } else {
            log.info("Payment {} has got new status.\nOperation time: {} ms", payment, timeMeasure.getLastTaskTimeMillis());
        }

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
