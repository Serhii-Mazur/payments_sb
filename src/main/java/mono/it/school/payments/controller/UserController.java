package mono.it.school.payments.controller;

import mono.it.school.payments.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class UserController {

    @PostMapping
    public String registerUser(User user) {
        return "user";
    }
}
