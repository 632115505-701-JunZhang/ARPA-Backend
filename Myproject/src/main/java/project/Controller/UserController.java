package project.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping("/register")
    public String RegisterController(String phone , String password) {
        return "This is Register Controller";

    }

    @PostMapping("/login")
    public String LoginController(String phone , String password) {
    if(phone==null || password==null) {
        return "Invalid phone or Password";
    }


    return null;
    }


}
