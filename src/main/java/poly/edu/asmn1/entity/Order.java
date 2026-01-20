package poly.edu.asmn1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String address;
    private String note;
    private Integer status;


    @Column(name = "Createdate")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}