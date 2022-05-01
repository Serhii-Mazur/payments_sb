package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.repository.PaymentRepository;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(Payment payment) { //TODO: Rewrite method to return boolean
        paymentRepository.save(payment);
    }
}
