package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Template;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.repository.TemplateRepository;
import mono.it.school.payments.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;

    }

    @Override
    public void save(Template template) { //TODO: Rewrite method to return boolean
        templateRepository.save(template);
    }


}
