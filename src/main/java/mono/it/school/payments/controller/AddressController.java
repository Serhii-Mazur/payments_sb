package mono.it.school.payments.controller;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

//    @PostMapping("/address/add")
//    public String addAddress(
//            @RequestParam("address") String address,
//            @RequestParam("e_mail") String eMail) {
//        addressService.save(new Address(null, address, eMail));
//
//        return "return/add";
//    }

    @PostMapping("/address/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addAddress(Address address) {
        addressService.save(address);
    }
}
