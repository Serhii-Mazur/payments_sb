package mono.it.school.payments.repository.impl;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.mapper.TemplateMapper;
import mono.it.school.payments.repository.TemplateRepository;
import mono.it.school.payments.repository.jpa.JpaTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
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
    public List<Template> getByAddressID(UUID addressID) {

        return jpaTemplateRepository.findByAddressID(addressID)
                .stream()
                .map(TemplateMapper::entityToTemplate)
                .collect(Collectors.toList());
    }

    @Override
    public Template getByTemplateNameAndAddressId(String templateName, UUID addressId) {

        return TemplateMapper.entityToTemplate(jpaTemplateRepository.findByTemplateNameAndAddressID(templateName, addressId));
    }
}
