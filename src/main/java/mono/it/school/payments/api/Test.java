package mono.it.school.payments.api;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.domain.Template;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.service.AddressService;
import mono.it.school.payments.service.PaymentService;
import mono.it.school.payments.service.TemplateService;
import mono.it.school.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@NoArgsConstructor
public class Test {

//    private final UserService userService;
//    private final AddressService addressService;
//    private final TemplateService templateService;
//    private final PaymentService paymentService;
//
//    @Autowired
//    public Test(UserService userService, AddressService addressService, TemplateService templateService, PaymentService paymentService) {
//        this.userService = userService;
//        this.addressService = addressService;
//        this.templateService = templateService;
//        this.paymentService = paymentService;
//    }


    @EventListener(ApplicationReadyEvent.class)
    private void eventTest() {
        System.out.println("Application started!");
//        userService.save(new User("test", "test", "test"));
//        addressService.save(new Address(null, "test address", "test"));
////        Template testTemplate = new Template(null, "")
//        List<Address> addressList = addressService.getByAddress("test address");
//        for (Address address : addressList) {
//            System.out.println(address.getAddressID());
//        }
//        List<Address> addrList = addressService.getByUserEmail("test");
//        for (Address address : addrList) {
//            System.out.println(address.getAddressID());
//        }
    }
}