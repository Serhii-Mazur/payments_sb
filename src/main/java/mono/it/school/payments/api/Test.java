package mono.it.school.payments.api;

import mono.it.school.payments.domain.Payment;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class Test {

    private final UserService userService;
    private final AddressService addressService;
    private final TemplateService templateService;
    private final PaymentService paymentService;
    private final JpaUserRepository jpaUserRepository;
    private final JpaTemplateRepository jpaTemplateRepository;
    private final JpaPaymentRepository jpaPaymentRepository;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    @Autowired
    public Test(UserService userService, AddressService addressService, TemplateService templateService, PaymentService paymentService, JpaUserRepository jpaUserRepository, JpaTemplateRepository jpaTemplateRepository, JpaPaymentRepository jpaPaymentRepository, RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.userService = userService;
        this.addressService = addressService;
        this.templateService = templateService;
        this.paymentService = paymentService;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaTemplateRepository = jpaTemplateRepository;
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }


    @EventListener(ApplicationReadyEvent.class)
    private void eventTest() {
        System.out.println("Application started!");

//        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        Payment result = new Payment();
//        List<Payment> payments = paymentService.getAll();
//        for (Payment payment : payments) {
//            System.out.println(payment);
//            HttpEntity<String> entity = new HttpEntity(payment, httpHeaders);
//            result = restTemplate.postForObject("http://localhost:8080/payment/handle", entity, Payment.class);
//        }
//        System.out.println(result);



//        Template template = new Template(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
//                "Payment for electric energy Kyiv, Korotka st. 39",
//                "Electricity Korotka",
//                "UA903052992990004149123456789");
//        System.out.println(template);
//        List<Address> all = addressService.getAll();
//        for (Address address : all) {
//            System.out.println(address.getAddressID());
//        }

    }
}