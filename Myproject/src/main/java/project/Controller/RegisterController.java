package project.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {


    @PostMapping("register")
    public String RegisterController() {
        return "This is Register Controller";

    }
}
