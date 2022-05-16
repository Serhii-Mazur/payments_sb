package mono.it.school.payments.service;

import lombok.SneakyThrows;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;

import java.util.List;

public interface PaymentService {
    Payment save(Payment payment);

    @SneakyThrows
    Payment update(Payment payment);

    List<Payment> getByStatus(PaymentStatus status);

    List<Payment> getAll();
}
