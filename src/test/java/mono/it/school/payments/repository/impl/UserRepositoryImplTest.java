package mono.it.school.payments.repository.impl;

import mono.it.school.payments.repository.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.junit.jupiter.Testcontainers;

//@Testcontainers
//@SpringBootTest(properties = {
//        "spring.datasource.url=jdbc:tc:postgresql:14///postgres",
//        "spring.datasource.username=postgres",
//        "spring.datasource.password=123456"
//})
public class UserRepositoryImplTest {

    private final UserRepositoryImpl instance;

    @Autowired
    public UserRepositoryImplTest(UserRepositoryImpl instance) {
        this.instance = instance;
    }


}
