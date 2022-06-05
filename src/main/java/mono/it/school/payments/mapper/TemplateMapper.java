package mono.it.school.payments.mapper;

import mono.it.school.payments.api.dto.TemplateDto;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.entity.TemplateEntity;

import java.util.UUID;

public class TemplateMapper {

    public static TemplateEntity templateToEntity(Template template) {
        TemplateEntity templateEntity;
        if (template == null) {
            templateEntity = null;
        } else {
            if (template.getTemplateID() == null) {
                templateEntity = new TemplateEntity(UUID.randomUUID(),
                        template.getAddressID(),
                        template.getPaymentPurpose(),
                        template.getTemplateName(),
                        template.getIban());
            } else {
                templateEntity = new TemplateEntity(template.getTemplateID(),
                        template.getAddressID(),
                        template.getPaymentPurpose(),
                        template.getTemplateName(),
                        template.getIban());
            }
        }

        return templateEntity;
    }

    public static Template entityToTemplate(TemplateEntity templateEntity) {
        Template template;
        if (templateEntity == null) {
            template = null;
        } else {
            template = new Template(templateEntity.getTemplateID(),
                    templateEntity.getAddressID(),
                    templateEntity.getPaymentPurpose(),
                    templateEntity.getTemplateName(),
                    templateEntity.getIban());
        }

        return template;
    }

    public static Template dtoToTemplate(TemplateDto templateDto) {
        Template template;
        if (templateDto == null) {
            template = null;
        } else {
            template = new Template(templateDto.getTemplateID(),
                    templateDto.getAddressID(),
                    templateDto.getPaymentPurpose(),
                    templateDto.getTemplateName(),
                    templateDto.getIban());
        }

        return template;
    }

    public static TemplateDto templateToDto(Template template) {
        TemplateDto templateDto;
        if (template == null) {
            templateDto = null;
        } else {
            templateDto = new TemplateDto(template.getTemplateID(),
                    template.getAddressID(),
                    template.getPaymentPurpose(),
                    template.getTemplateName(),
                    template.getIban());
        }

        return templateDto;
    }
}