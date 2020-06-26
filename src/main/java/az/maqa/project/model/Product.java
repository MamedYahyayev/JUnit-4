package az.maqa.project.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    private String name;
    private float price;

    public Product() {

    }

    public Product(String name, float price) {
        this.price = price;
        this.name = name;
    }


}
