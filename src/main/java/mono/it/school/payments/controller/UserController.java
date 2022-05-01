package mono.it.school.payments.controller;

import mono.it.school.payments.domain.User;
import mono.it.school.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //  Not understand it
//    @PostMapping("/user/add")
//    @ResponseBody()
//    public User addUser(User user) {
//        userService.save(user);
//
//        return user;
//    }

    //  It works
    @PostMapping("/user/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addUser(User user) {
        userService.save(user);
    }

//    @PostMapping("/user/add")
//    public String addUser(User user) {
//        userService.save(user);
//
//        return "user";
//    }
}
