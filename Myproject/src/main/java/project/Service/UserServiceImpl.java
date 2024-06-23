package project.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entity.User;
import project.Mapepr.UserServiceMapper;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserServiceMapper userServiceMapper;


    @Override
    public User getUserById(int id) {

        return null;
    }
}
