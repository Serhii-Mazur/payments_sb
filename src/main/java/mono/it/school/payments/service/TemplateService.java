package mono.it.school.payments.service;

import mono.it.school.payments.domain.Template;

public interface TemplateService {
    void save(Template template);

    Template getByTemplateName(String templateName);
}
