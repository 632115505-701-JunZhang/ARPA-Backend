package project.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.Entity.User;
import project.Services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //注册功能
    @PostMapping("/register")
    public String RegisterController(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return "Invalid email or password";
        } else {
            if (userService.checkUser(user)) {
                return "Account already exists";
            } else {
                userService.saveUser(user);
                return "Register successful";
            }
        }
    }



    @PostMapping("/login")
    public String LoginController(String phone , String password) {
    if(phone==null || password==null) {
        return "Invalid phone or Password";
    }


    return null;
    }


}
