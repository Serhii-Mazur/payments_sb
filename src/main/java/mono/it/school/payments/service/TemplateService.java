package mono.it.school.payments.service;

import lombok.SneakyThrows;
import mono.it.school.payments.domain.Template;

import java.util.List;
import java.util.UUID;

public interface TemplateService {
    Template save(Template template);

    @SneakyThrows
    Template update(Template template);

    List<Template> getAll();

    List<Template> getByAddressID(UUID addressID);

    Template getByTemplateName(String templateName);

    Template getByTemplateNameAndAddressID(String templateName, UUID addressId);
}
