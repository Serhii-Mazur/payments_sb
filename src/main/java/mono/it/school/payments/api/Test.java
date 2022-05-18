package mono.it.school.payments.api;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.TemplateEntity;
import mono.it.school.payments.repository.jpa.JpaPaymentRepository;
import mono.it.school.payments.repository.jpa.JpaTemplateRepository;
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
import java.util.UUID;

@Component
public class Test {

    private final UserService userService;
    private final AddressService addressService;
    private final TemplateService templateService;
    private final PaymentService paymentService;
    private final JpaUserRepository jpaUserRepository;
    private final JpaTemplateRepository jpaTemplateRepository;
    private final JpaPaymentRepository jpaPaymentRepository;

    @Autowired
    public Test(UserService userService, AddressService addressService, TemplateService templateService, PaymentService paymentService, JpaUserRepository jpaUserRepository, JpaTemplateRepository jpaTemplateRepository, JpaPaymentRepository jpaPaymentRepository) {
        this.userService = userService;
        this.addressService = addressService;
        this.templateService = templateService;
        this.paymentService = paymentService;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaTemplateRepository = jpaTemplateRepository;
        this.jpaPaymentRepository = jpaPaymentRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    private void eventTest() {
        System.out.println("Application started!");
        List<Address> all = addressService.getAll();
        for (Address address : all) {
            System.out.println(address.getAddressID());
        }

//        TemplateEntity template = jpaTemplateRepository.findByTemplateNameAndAddressID("test", UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));
//        System.out.println(template.getTemplateID());
//        List<User> all = userService.getAll();
//        System.out.println(all.size());

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