package mono.it.school.payments.service;

import mono.it.school.payments.domain.Payment;

import java.util.List;

public interface PaymentService {
    void save(Payment payment);

    List<Payment> getByStatus(String status);
}
