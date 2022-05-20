package mono.it.school.payments.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.exception.InvalidEntityException;
import mono.it.school.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/user")
@Api
public class UserController {

    private final UserService userService;
    private final StopWatch timeMeasure;

    @Autowired
    public UserController(UserService userService, StopWatch timeMeasure) {
        this.userService = userService;
        this.timeMeasure = timeMeasure;
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation("Get all Users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @SneakyThrows
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("Add new User")
    public User addUser(@RequestBody @Valid User user,
                        BindingResult bindingResult) {
        User savedUser;
        if (bindingResult.hasErrors()) {
            log.warn("Attempt to save invalid User: {}", user);
            throw new InvalidEntityException("Invalid User. One or more fields do not match the requirements.");
        } else {
            timeMeasure.start("userSaving");
            savedUser = userService.save(user);
            timeMeasure.stop();
            if (savedUser != null) {
                log.info("User saved: {}\nOperation time: {} ms", savedUser, timeMeasure.getLastTaskTimeMillis());
            }
        }

        return savedUser;
    }
}
