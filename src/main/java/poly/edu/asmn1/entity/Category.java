package poly.edu.asmn1.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}