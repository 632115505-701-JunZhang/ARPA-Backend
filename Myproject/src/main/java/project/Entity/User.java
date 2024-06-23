package project.Entity;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Entity
@Table(name="user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "pwd")
    private String password;
}
