package User.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "user_tlb")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}
