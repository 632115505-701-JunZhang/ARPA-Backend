package project.Service;

import project.Entity.User;

public interface UserService {

    public boolean addUser(User user);


    public User getUserById(int id);

}
