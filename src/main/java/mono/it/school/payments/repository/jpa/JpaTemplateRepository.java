package mono.it.school.payments.repository.jpa;

import mono.it.school.payments.domain.Template;
import mono.it.school.payments.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTemplateRepository extends JpaRepository<TemplateEntity, UUID> {

    TemplateEntity findByTemplateNameAndAddressID(String templateName, UUID addressId);

    List<TemplateEntity> findByAddressID(UUID addressID);
}
