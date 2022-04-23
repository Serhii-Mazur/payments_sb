package mono.it.school.payments.mapper;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.entity.TemplateEntity;

public class TemplateMapper {

    public static TemplateEntity templateToEntity(Template template) {

        return new TemplateEntity(template.getTemplateID(),
                template.getAddressID(),
                template.getPaymentPurpose(),
                template.getTemplateName(),
                template.getIban());
    }

    public static Template entityToTemplate(TemplateEntity templateEntity) {

        return new Template(templateEntity.getTemplateID(),
                templateEntity.getAddressID(),
                templateEntity.getPaymentPurpose(),
                templateEntity.getTemplateName(),
                templateEntity.getIban());
    }
}