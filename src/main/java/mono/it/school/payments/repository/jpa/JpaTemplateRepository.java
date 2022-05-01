package mono.it.school.payments.repository.jpa;

import mono.it.school.payments.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTemplateRepository extends JpaRepository<TemplateEntity, UUID> {

    TemplateEntity findByTemplateName(String templateName);
}
