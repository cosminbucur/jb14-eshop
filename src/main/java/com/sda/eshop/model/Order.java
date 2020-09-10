package com.sda.eshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Order")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    // need a user and a list of products
    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;

        // calculate total price
        // get price from all products
        this.totalPrice = products.stream()
                .mapToDouble(product -> product.getPrice())
                .sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // just for example
    private Double calculateTotalPrice(List<Product> products) {
        Double result = 0.0;
        // item : collection - enhanced for
        for (Product product : products) {
            result += product.getPrice();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, user);
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", totalPrice=" + totalPrice +
            '}';
    }
}
