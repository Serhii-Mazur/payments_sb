package mono.it.school.payments.repository.impl;

import mono.it.school.payments.domain.Template;
import mono.it.school.payments.mapper.TemplateMapper;
import mono.it.school.payments.repository.TemplateRepository;
import mono.it.school.payments.repository.jpa.JpaTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TemplateRepositoryImpl implements TemplateRepository {

    private final JpaTemplateRepository jpaTemplateRepository;

    @Autowired
    public TemplateRepositoryImpl(JpaTemplateRepository jpaTemplateRepository) {
        this.jpaTemplateRepository = jpaTemplateRepository;
    }

    @Override
    public Template save(Template template) {

        return TemplateMapper.entityToTemplate(jpaTemplateRepository.save(TemplateMapper.templateToEntity(template)));
    }

    @Override
    public List<Template> getAll() {

        return jpaTemplateRepository.findAll()
                .stream()
                .map(TemplateMapper::entityToTemplate)
                .collect(Collectors.toList());
    }

    @Override
    public Template getById(UUID id) {

        return TemplateMapper.entityToTemplate(jpaTemplateRepository.getById(id));
    }

    @Override
    public Template getByTemplateName(String templateName) {

        return TemplateMapper.entityToTemplate(jpaTemplateRepository.findByTemplateName(templateName));
    }

    @Override
    public Template getByTemplateNameAndAddressId(String templateName, UUID addressId) {

        return TemplateMapper.entityToTemplate(jpaTemplateRepository.findByTemplateNameAndAddressID(templateName, addressId));
    }
}
