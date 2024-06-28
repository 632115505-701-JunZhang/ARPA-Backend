package project.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entity.User;
import project.Mapepr.UserServiceMapper;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserServiceMapper userServiceMapper;



    @Override
    public boolean checkUser(User user) {
        return userServiceMapper.findByEmail(user.getEmail()) != null;
    }

    @Override
    public User saveUser(User user) {
        return userServiceMapper.save(user);
    }
}
