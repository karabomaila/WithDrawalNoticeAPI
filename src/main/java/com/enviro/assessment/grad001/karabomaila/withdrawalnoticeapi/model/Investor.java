package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "contact")
    private String contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "investor", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Product> products;

    public boolean isAmountGreater(String productType, double amount) {
        for (Product product: products){
            if (product.getType().equals(productType) && product.getCurrentBalance() > amount) return false;
        }
        return true;
    }

    public Product getProductByType(String productType){
        for (Product product: products){
            if (product.getType().equalsIgnoreCase(productType)) return product;
        }
        return null;
    }

    public void UpdateAge() {
        age++;
    }

    public void addProduct(Product product) {
        if (products == null){
            products = new ArrayList<>();
        }
        products.add(product);
    }
}
