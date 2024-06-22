package project.Service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService extends JpaRepository {



    User getUser(int id);
}
