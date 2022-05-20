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
    public List<Template> getAllTemplates() {

        return templateService.getAll();
    }

    @GetMapping("/all/byaddress")
    @ResponseBody
    public List<Template> getAllTemplatesByAddressID(@RequestBody UUID addressID) {

        return templateService.getByAddressID(addressID);
    }


    @PostMapping("/add")
    @ResponseBody
    @SneakyThrows
    public Template addNewTemplate(@RequestBody @Valid Template template,
                               BindingResult bindingResult) {
        Template savedTemplate;
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid Template: {}", template);
            throw new InvalidEntityException("Invalid Template. One or more fields do not match the requirements.");
        } else {
            //TODO: Measure the execution time
            savedTemplate = templateService.save(template);
            log.info("Template saved: {}", savedTemplate);
        }

        return savedTemplate;
    }
}
