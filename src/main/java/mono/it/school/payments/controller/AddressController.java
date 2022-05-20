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
import java.util.List;

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
    public List<Address> getAllAddresses() {

        return addressService.getAll();
    }

    @GetMapping("/all/byuser")
    @ResponseBody
    public List<Address> getAllAddressesByUser(@RequestBody String userEmail) {

        return addressService.getByUserEmail(userEmail);
    }

    @SneakyThrows
    @PostMapping("/add")
    @ResponseBody
    public Address addAddress(@RequestBody @Valid Address address,
                           BindingResult bindingResult) {
        Address savedAddress;
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid Address: {}", address);
            throw new InvalidEntityException("Invalid Address. One or more fields do not match the requirements.");
        } else {
            long start = System.nanoTime();
            savedAddress = addressService.save(address);
            long end = System.nanoTime();
            if (savedAddress != null) {
                log.info("Address saved: {}", savedAddress);
            }
        }

        return savedAddress;
    }
}
