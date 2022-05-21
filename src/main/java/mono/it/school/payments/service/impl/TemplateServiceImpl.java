package mono.it.school.payments.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.repository.TemplateRepository;
import mono.it.school.payments.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;

    }

    @Override
    @SneakyThrows
    public Template save(Template template) {
        if (exists(template)) {
            log.warn("Attempt to update non-existing Template {}", template);
            throw new TemplateServiceException(String.format("Template with name [%s] is already exists!", template.getTemplateName()));
        }

        return templateRepository.save(template);
    }

    @Override
    @SneakyThrows
    public Template update(Template template) {
        if (!exists(template)) {
            log.warn("Attempt to update non-existing Template {}", template);
            throw new TemplateServiceException(String.format("Template with name [%s] does not exist!", template.getTemplateName()));
        }

        return templateRepository.save(template);
    }

    @Override
    public List<Template> getAll() {

        return templateRepository.getAll();
    }

    @Override
    public List<Template> getByAddressID(UUID addressID) {

        return templateRepository.getByAddressID(addressID);
    }

    @Override
    public Template getByTemplateNameAndAddressID(String templateName, UUID addressId) {

        return templateRepository.getByTemplateNameAndAddressId(templateName, addressId);
    }

    private boolean exists(Template template) {
        boolean result = false;
        Template templateFromDb = templateRepository.getByTemplateNameAndAddressId(template.getTemplateName(), template.getAddressID());
        if (templateFromDb != null) {
            if (template.getTemplateID() != null) {
                if (template.equals(templateFromDb)) {
                    result = true;
                }
            } else if (template.getTemplateName().equals(templateFromDb.getTemplateName())
                    && template.getAddressID().equals(templateFromDb.getAddressID())) {
                result = true;
            }
        }

        return result;
    }

    public static class TemplateServiceException extends Exception {
        public TemplateServiceException(String message) {
            super(message);
        }
    }
}
