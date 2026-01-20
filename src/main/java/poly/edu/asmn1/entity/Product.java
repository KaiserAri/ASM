package poly.edu.asmn1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image; // Lưu URL Cloudinary
    private Double price;

    @Column(name = "Createdate")
    private Date createDate = new Date();

    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}