package mono.it.school.payments.repository.jpa;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, UUID> {

    List<PaymentEntity> findAllByPaymentStatus(PaymentStatus status);

    PaymentEntity findByTemplateIDAndDescription(UUID templateID, String description);
}
