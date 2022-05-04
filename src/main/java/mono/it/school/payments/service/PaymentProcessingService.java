package mono.it.school.payments.service;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentProcessingService implements Runnable{

    private final PaymentService paymentService;

    @Autowired
    public PaymentProcessingService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Override
    public void run() {
        PaymentStatus status = PaymentStatus.NEW;
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();    //TODO: Logging here
            }

            for (Payment payment : paymentService.getByStatus(status.name())) {

            }
        }
    }
}
