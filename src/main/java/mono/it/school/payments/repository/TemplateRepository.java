package mono.it.school.payments.repository;

import mono.it.school.payments.domain.Template;

import java.util.List;
import java.util.UUID;

public interface TemplateRepository {
    Template save(Template template);

    List<Template> getAll();

    List<Template> getByAddressID(UUID addressID);

    Template getByTemplateNameAndAddressId(String templateName, UUID addressId);
}
