package mono.it.school.payments.controller;

import mono.it.school.payments.domain.User;
import mono.it.school.payments.service.UserService;
import mono.it.school.payments.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @ResponseBody
    public void getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAll());
    }

    @PostMapping("/add")
    @ResponseBody
    public void addUser(User user) {
        if (UserValidation.validate(user)) {
            userService.save(user);
        }
    }
}
