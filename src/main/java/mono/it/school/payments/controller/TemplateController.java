package mono.it.school.payments.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.service.AddressService;
import mono.it.school.payments.service.TemplateService;
import mono.it.school.payments.validation.TemplateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@RequestMapping(value = "/template")
public class TemplateController {

    private final AddressService addressService;
    private final TemplateService templateService;

    @Autowired
    public TemplateController(AddressService addressService, TemplateService templateService) {
        this.addressService = addressService;
        this.templateService = templateService;
    }

    @GetMapping("/all")
    @ResponseBody
    public void getAllTemplates(Model model) {
        model.addAttribute("allTemplates", templateService.getAll());
    }

    @PostMapping("/add")
    @ResponseBody
    public void addNewTemplate(@RequestParam("template_name") String templateName,
                               @RequestParam("payment_purpose") String paymentPurpose,
                               @RequestParam("iban") String iban,
                               @RequestParam("address") String address,
                               Model model) {

        Template template = new Template(null,
                addressService.getByAddress(address).getAddressID(),
                paymentPurpose,
                templateName,
                iban);

        templateService.save(template);

    }
}
