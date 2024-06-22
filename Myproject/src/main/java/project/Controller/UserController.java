package project.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping("/register")
    public String RegisterController() {
        return "This is Register Controller";

    }

    @PostMapping("/login")
    public String LoginController() {
        return ;

    }

}
