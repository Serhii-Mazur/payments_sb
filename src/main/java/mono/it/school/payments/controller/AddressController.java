package mono.it.school.payments.controller;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.service.AddressService;
import mono.it.school.payments.validation.AddressValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    @ResponseBody
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addAddress(Address address) {
        if(AddressValidation.validate(address)) {
            addressService.save(address);
        }
    }
}
