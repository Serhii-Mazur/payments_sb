package mono.it.school.payments.repository.impl;

import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.entity.PaymentEntity;
import mono.it.school.payments.mapper.PaymentMapper;
import mono.it.school.payments.repository.PaymentRepository;
import mono.it.school.payments.repository.jpa.JpaPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository jpaPaymentRepository;

    @Autowired
    public PaymentRepositoryImpl(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity savedPayment = jpaPaymentRepository.save(PaymentMapper.paymentToEntity(payment));

        return PaymentMapper.entityToPayment(savedPayment);
    }

    @Override
    public Payment getById(UUID id) {
        PaymentEntity paymentFromDb = jpaPaymentRepository.getById(id);

        return PaymentMapper.entityToPayment(paymentFromDb);
    }

    @Override
    public List<Payment> getByStatus(String status) {

        return jpaPaymentRepository.findAllByPaymentStatus(status)
                .stream()
                .map(PaymentMapper::entityToPayment)
                .collect(Collectors.toList());
    }
}
