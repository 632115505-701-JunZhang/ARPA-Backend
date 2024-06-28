package project.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Entity.User;
import project.Services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String RegisterController(String email , String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return "Invalid email or password";
        }
        User user = new User(email, password);
        if (userService.checkUser(user)) {
            return "Account already exists";
        }else{
           userService.saveUser(user);
        }
        return "register successful";
    }

    @PostMapping("/login")
    public String LoginController(String phone , String password) {
    if(phone==null || password==null) {
        return "Invalid phone or Password";
    }


    return null;
    }


}
