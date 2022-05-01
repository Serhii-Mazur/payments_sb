package mono.it.school.payments.service;

import mono.it.school.payments.domain.Payment;

public interface PaymentService {
    void save(Payment payment);
}
