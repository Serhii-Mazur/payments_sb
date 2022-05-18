package mono.it.school.payments.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping(value = "/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/all")
    @ResponseBody
    public void getAllAddresses(Model model) {
        model.addAttribute("allAddresses", addressService.getAll());
    }

    @SneakyThrows
    @PostMapping("/add")
    @ResponseBody
    public void addAddress(@Valid Address address,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            log.warn(new StringBuilder("Attempt to save invalid Address:").append("\n")
                    .append("Address: ").append(address.getAddress()).append("\n")
                    .append("UserEmail: ").append(address.getUserEmail()).append("\n")
            );
            throw new InvalidEntityException("Invalid Address. One or more fields do not match the requirements.");
        } else {
            long start = System.nanoTime();
            Address savedAddress = addressService.save(address);
            long end = System.nanoTime();
            if (savedAddress != null) {
                log.info(new StringBuilder("Address saved: ").append("\n")
                        .append(savedAddress.getAddressID()).append("\n")
                        .append(savedAddress.getAddress()).append("\n")
                        .append(savedAddress.getUserEmail()).append("\n")
                        .append("Operation time: ").append(((end - start) / 1000)).append(" ms"));
            }
            model.addAttribute("savedAddress", savedAddress);
        }
    }
}
