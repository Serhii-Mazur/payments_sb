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
import java.util.List;

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
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @SneakyThrows
    @PostMapping("/add")
    @ResponseBody
    public User addUser(@RequestBody @Valid User user,
                        BindingResult bindingResult) {
        User savedUser;
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid User: {}", user);
            throw new InvalidEntityException("Invalid User. One or more fields do not match the requirements.");
        } else {
            long start = System.nanoTime();
            savedUser = userService.save(user);
            long end = System.nanoTime();
            if (savedUser != null) {
                log.info("User saved: {}\nOperation time: {} ms", savedUser, ((end - start) / 1000));
            }
        }

        return savedUser;
    }
}
