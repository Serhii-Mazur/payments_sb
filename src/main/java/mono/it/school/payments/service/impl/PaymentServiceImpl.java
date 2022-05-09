package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.repository.PaymentRepository;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) { //TODO: Rewrite method to return boolean
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getByStatus(String status) {

        return paymentRepository.getByStatus(status);
    }
}
