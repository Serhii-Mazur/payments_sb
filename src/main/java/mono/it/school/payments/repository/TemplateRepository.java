package mono.it.school.payments.repository;

import mono.it.school.payments.domain.Template;

import java.util.UUID;

public interface TemplateRepository {
    Template save(Template template);

    Template getById(UUID id);

    Template getByTemplateName(String templateName);
}
