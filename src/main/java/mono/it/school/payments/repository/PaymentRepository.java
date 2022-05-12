package mono.it.school.payments.repository;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);

    List<Payment> getAll();

    Payment getById(UUID id);

    List<Payment> getByStatus(PaymentStatus status);
}
