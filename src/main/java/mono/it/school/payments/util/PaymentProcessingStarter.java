package mono.it.school.payments.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class PaymentProcessingStarter {

    @EventListener(ApplicationReadyEvent.class)
    public void processingStart() {

    }
}
