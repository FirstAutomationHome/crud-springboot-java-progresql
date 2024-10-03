package com.fdymain.crud_springboot.product;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private int price;

    private LocalDate createdDate;
    @Transient
    private int storageTime;

    public Products() {
    }

    public Products(Long id, String name, int price, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdDate = createdDate;

    }

    public Products(String name, int price, LocalDate createdDate) {
        this.name = name;
        this.price = price;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreateDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getStorageTime() {
        return Period.between(this.createdDate, LocalDate.now()).getYears();
    }

    public void setStorageTime(int storageTime) {
        this.storageTime = storageTime;
    }
}
