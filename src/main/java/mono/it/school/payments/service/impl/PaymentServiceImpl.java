package mono.it.school.payments.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.repository.PaymentRepository;
import mono.it.school.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @SneakyThrows
    public Payment save(Payment payment) {
        if (exists(payment)) {
            log.warn("Attempt to add existing Payment {}", payment);
            throw new PaymentServiceException(String.format("Payment with such parameters [templateID=%s, description=%s] is already exists!",
                    payment.getTemplateID(),
                    payment.getDescription())
            );
        }

        return paymentRepository.save(payment);
    }

    @Override
    @SneakyThrows
    public Payment update(Payment payment) {
        if (!exists(payment)) {
            log.warn("Attempt to update non-existing Payment {}", payment);
            throw new PaymentServiceException(String.format("Payment with such parameters [templateID=%s, description=%s] does not exist!",
                    payment.getTemplateID(),
                    payment.getDescription())
            );
        }

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getByStatus(PaymentStatus status) {

        return paymentRepository.getByStatus(status);
    }

    @Override
    public List<Payment> getAll() {

        return paymentRepository.getAll();
    }

    private boolean exists(Payment payment) {
        boolean result = false;
        Payment paymentfromDB = paymentRepository.getByTemplateIDAndDescription(payment.getTemplateID(), payment.getDescription());
        if (paymentfromDB != null) {
            if (payment.getPaymentID() != null) {
                if (payment.getPaymentID().equals(paymentfromDB.getPaymentID())
                        && payment.getTemplateID().equals(paymentfromDB.getTemplateID())
                        && payment.getDescription().equals(paymentfromDB.getDescription())) {
                    result = true;
                }
            } else if (payment.getTemplateID().equals(paymentfromDB.getTemplateID())
                    && payment.getDescription().equals(paymentfromDB.getDescription())) {
                result = true;
            }
        }

        return result;
    }

    public class PaymentServiceException extends Exception {
        public PaymentServiceException() {
            super();
        }

        public PaymentServiceException(String message) {
            super(message);
        }

        public PaymentServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public PaymentServiceException(Throwable cause) {
            super(cause);
        }
    }
}
