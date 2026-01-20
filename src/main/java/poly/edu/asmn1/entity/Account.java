package poly.edu.asmn1.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "Username", length = 50)
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String photo;
    private Boolean activated;
    private Boolean role;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}