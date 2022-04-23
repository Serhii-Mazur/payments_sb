package mono.it.school.payments.repository;

import mono.it.school.payments.domain.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
