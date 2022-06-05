package mono.it.school.payments.mapper;

import mono.it.school.payments.api.dto.PaymentDto;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.entity.PaymentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentMapper {

    public static PaymentEntity paymentToEntity(Payment payment) {
        PaymentEntity paymentEntity;
        LocalDateTime createdDateTime = LocalDateTime.now();
        LocalDateTime etlDateTime = LocalDateTime.now();

        if (payment == null) {
            paymentEntity = null;
        } else {
            if (payment.getPaymentID() == null) {
                if (payment.getCreatedDateTime() != null) {
                    createdDateTime = payment.getCreatedDateTime();
                }
                paymentEntity = new PaymentEntity(UUID.randomUUID(),
                        payment.getDescription(),
                        payment.getTemplateID(),
                        payment.getCardNumber(),
                        payment.getPaymentAmount(),
                        PaymentStatus.NEW,
                        createdDateTime,
                        etlDateTime);
            } else {
                paymentEntity = new PaymentEntity(payment.getPaymentID(),
                        payment.getDescription(),
                        payment.getTemplateID(),
                        payment.getCardNumber(),
                        payment.getPaymentAmount(),
                        payment.getPaymentStatus(),
                        createdDateTime,
                        etlDateTime);
            }
        }

        return paymentEntity;
    }

    public static Payment entityToPayment(PaymentEntity paymentEntity) {
        Payment payment;
        if (paymentEntity == null) {
            payment = null;
        } else {
            payment = new Payment(paymentEntity.getPaymentID(),
                    paymentEntity.getDescription(),
                    paymentEntity.getTemplateID(),
                    paymentEntity.getCardNumber(),
                    paymentEntity.getPaymentAmount(),
                    paymentEntity.getPaymentStatus(),
                    paymentEntity.getCreatedDateTime(),
                    paymentEntity.getEtlDateTime());
        }

        return payment;
    }

    public static Payment dtoToPayment(PaymentDto paymentDto) {
        Payment payment;
        if (paymentDto == null) {
            payment = null;
        } else {
            payment = new Payment(paymentDto.getPaymentID(),
                    paymentDto.getDescription(),
                    paymentDto.getTemplateID(),
                    paymentDto.getCardNumber(),
                    paymentDto.getPaymentAmount(),
                    paymentDto.getPaymentStatus(),
                    paymentDto.getCreatedDateTime(),
                    paymentDto.getEtlDateTime());
        }

        return payment;
    }

    public static PaymentDto paymentToDto(Payment payment) {
        PaymentDto paymentDto;
        if (payment == null) {
            paymentDto = null;
        } else {
            paymentDto = new PaymentDto(payment.getPaymentID(),
                    payment.getDescription(),
                    payment.getTemplateID(),
                    payment.getCardNumber(),
                    payment.getPaymentAmount(),
                    payment.getPaymentStatus(),
                    payment.getCreatedDateTime(),
                    payment.getEtlDateTime());
        }

        return paymentDto;
    }
}
