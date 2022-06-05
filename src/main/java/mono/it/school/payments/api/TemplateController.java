package mono.it.school.payments.api;

import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.api.dto.TemplateDto;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.mapper.TemplateMapper;
import mono.it.school.payments.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping(value = "/template")
public class TemplateController {

    private final TemplateService templateService;
    private final StopWatch timeMeasure;

    @Autowired
    public TemplateController(TemplateService templateService, StopWatch timeMeasure) {
        this.templateService = templateService;
        this.timeMeasure = timeMeasure;
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation("Get all Templates")
    public List<TemplateDto> getAllTemplates() {

        return templateService.getAll()
                .stream()
                .map(TemplateMapper::templateToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/all/byaddress")
    @ResponseBody
    @ApiOperation("Get all Templates by address (identifier: addressID)")
    public List<TemplateDto> getAllTemplatesByAddressID(@RequestBody UUID addressID) {

        return templateService.getByAddressID(addressID)
                .stream()
                .map(TemplateMapper::templateToDto)
                .collect(Collectors.toList());
    }


    @PostMapping("/add")
    @ResponseBody
    @SneakyThrows
    @ApiOperation("Add new Template")
    public TemplateDto addNewTemplate(@RequestBody @Valid TemplateDto templateDto,
                               BindingResult bindingResult) {
        Template savedTemplate;
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid Template: {}", templateDto);
            throw new InvalidEntityException("Invalid Template. One or more fields do not match the requirements.");
        } else {
            timeMeasure.start("templateSaving");
            savedTemplate = templateService.save(TemplateMapper.dtoToTemplate(templateDto));
            timeMeasure.stop();
            log.info("Template saved: {}\nOperation time: {} ms", savedTemplate, timeMeasure.getLastTaskTimeMillis());
        }

        return TemplateMapper.templateToDto(savedTemplate);
    }
}
