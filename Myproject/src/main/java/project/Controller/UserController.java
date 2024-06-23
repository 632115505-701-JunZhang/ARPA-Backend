package project.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/User")
public class UserController {


    @PostMapping("/register")
    public String RegisterController() {
        return "This is Register Controller";

    }

    @PostMapping("/login")
    public String LoginController(String email , String password) {
    if(email==null || password==null) {
        return "Invalid Email or Password";
    }


    return null;
    }

}
