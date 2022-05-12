package mono.it.school.payments.api;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.entity.PaymentEntity;
import mono.it.school.payments.entity.UserEntity;
import mono.it.school.payments.repository.jpa.JpaPaymentRepository;
import mono.it.school.payments.repository.jpa.JpaUserRepository;
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

    private final UserService userService;
    private final AddressService addressService;
    private final TemplateService templateService;
    private final PaymentService paymentService;
    private final JpaUserRepository jpaUserRepository;
    private final JpaPaymentRepository jpaPaymentRepository;

    @Autowired
    public Test(UserService userService, AddressService addressService, TemplateService templateService, PaymentService paymentService, JpaUserRepository jpaUserRepository, JpaPaymentRepository jpaPaymentRepository) {
        this.userService = userService;
        this.addressService = addressService;
        this.templateService = templateService;
        this.paymentService = paymentService;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaPaymentRepository = jpaPaymentRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    private void eventTest() {
        System.out.println("Application started!");

        List<User> all = userService.getAll();
        System.out.println(all.size());

//        userService.save(new User("test", "test", "test"));

//        UserEntity byEmail = jpaUserRepository.getByEmail("123");
//        List<PaymentEntity> paymentEntities = jpaPaymentRepository.findAllByPaymentStatus(PaymentStatus.NEW);
//        for (PaymentEntity paymentEntity : paymentEntities) {
//            System.out.println(paymentEntity.getPaymentID());
//        }
//        System.out.println(templateService.getByTemplateName("test").getTemplateID());

//        for (Payment payment : paymentService.getByStatus("NEW")) {
//            System.out.println(payment.getPaymentID());
//        }


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