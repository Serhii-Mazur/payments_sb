package mono.it.school.payments.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.service.UserService;
import mono.it.school.payments.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
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

    @SneakyThrows
    @PostMapping("/add")
    @ResponseBody
    public void addUser(@Valid User user,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            log.warn(new StringBuilder("Attempt to save invalid User:").append("\n")
                    .append("fullName: ").append(user.getFullName()).append("\n")
                    .append("email: ").append(user.getEmail()).append("\n")
                    .append("phoneNumber: ").append(user.getPhoneNumber())
            );
            throw new InvalidEntityException("Invalid User. One or more fields do not match the requirements.");
        } else {
            long start = System.nanoTime();
            User savedUser = userService.save(user);
            long end = System.nanoTime();
            if (savedUser != null) {
                log.info(new StringBuilder("User saved: ").append("\n")
                        .append(savedUser.getFullName()).append("\n")
                        .append(savedUser.getEmail()).append("\n")
                        .append(savedUser.getPhoneNumber()).append("\n")
                        .append("Operation time: ").append(((end - start) / 1000)).append(" ms"));
            }
            model.addAttribute("savedUser", savedUser);
        }
    }
}
