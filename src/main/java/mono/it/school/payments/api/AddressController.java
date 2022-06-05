package mono.it.school.payments.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.api.dto.AddressDto;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.mapper.AddressMapper;
import mono.it.school.payments.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping(value = "/address")
public class AddressController {

    private final AddressService addressService;
    private final StopWatch timeMeasure;

    @Autowired
    public AddressController(AddressService addressService, StopWatch timeMeasure) {
        this.addressService = addressService;
        this.timeMeasure = timeMeasure;
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation("Get all Addresses")
    public List<Address> getAllAddresses() {

        return addressService.getAll();
    }

    @GetMapping("/all/byuser")
    @ResponseBody
    @ApiOperation("Get all Addresses by User (identifier: userEmail)")
    public List<AddressDto> getAllAddressesByUser(@RequestBody String userEmail) {

        return addressService.getByUserEmail(userEmail)
                .stream()
                .map(AddressMapper::addressToDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("Add new Address")
    public AddressDto addAddress(@RequestBody @Valid AddressDto addressDto,
                           BindingResult bindingResult) {
        Address savedAddress;
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid Address: {}", addressDto);
            throw new InvalidEntityException("Invalid Address. One or more fields do not match the requirements.");
        } else {
            timeMeasure.start("addressSaving");
            savedAddress = addressService.save(AddressMapper.dtoToAddress(addressDto));
            timeMeasure.stop();
            if (savedAddress != null) {
                log.info("Address saved: {}\nOperation time: {} ms", savedAddress, timeMeasure.getLastTaskTimeMillis());
            }
        }

        return AddressMapper.addressToDto(savedAddress);
    }
}
