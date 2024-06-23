package project.Mapepr;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.Entity.User;

@Repository
public interface UserServiceMapper extends JpaRepository<User, Long> {
}
