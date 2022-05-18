package mono.it.school.payments.mapper;

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
}