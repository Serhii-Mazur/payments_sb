package mono.it.school.payments.controller;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.service.AddressService;
import mono.it.school.payments.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Controller
public class TemplateController {

    private final AddressService addressService;
    private final TemplateService templateService;

    @Autowired
    public TemplateController(AddressService addressService, TemplateService templateService) {
        this.addressService = addressService;
        this.templateService = templateService;
    }

    @PostMapping("/template/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addNewTemplate(@RequestParam("template_name") String templateName,
                               @RequestParam("payment_purpose") String paymentPurpose,
                               @RequestParam("iban") String iban,
                               @RequestParam("address") String address) {

        templateService.save(new Template(null,
                addressService.getByAddress(address).getAddressID(),
                paymentPurpose,
                templateName,
                iban));
    }
}
