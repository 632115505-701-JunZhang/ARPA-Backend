package project.Dao;


import jakarta.persistence.Table;
import org.springframework.stereotype.Repository;

@Repository
@Table()
public class User {
    private int id;

    private String phone;

    private String password;
}
