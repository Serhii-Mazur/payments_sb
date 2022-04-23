package mono.it.school.payments.mapper;

import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.entity.PaymentEntity;

public class PaymentMapper {

    public static PaymentEntity paymentToEntity(Payment payment) {

        return new PaymentEntity(payment.getPaymentID(),
                payment.getTemplateID(),
                payment.getCardNumber(),
                payment.getPaymentAmount(),
                payment.getPaymentStatus(),
                payment.getCreatedDateTime(),
                payment.getEtlDateTime());
    }

    public static Payment entityToPayment(PaymentEntity paymentEntity) {

        return new Payment(paymentEntity.getPaymentID(),
                paymentEntity.getTemplateID(),
                paymentEntity.getCardNumber(),
                paymentEntity.getPaymentAmount(),
                paymentEntity.getPaymentStatus(),
                paymentEntity.getCreatedDateTime(),
                paymentEntity.getEtlDateTime());
    }
}
